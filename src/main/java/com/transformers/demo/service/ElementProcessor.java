package com.transformers.demo.service;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.transformers.demo.config.AppConfig;
import com.transformers.demo.model.Elements;
import com.transformers.demo.model.ResponseElement;
import com.transformers.demo.model.transformer.*;

@Service
public class ElementProcessor {

    public ElementProcessor(AppConfig config) {}

    public ResponseElement[] handleTransformations(Elements[] inputArray) {
        ResponseElement[] responses = new ResponseElement[inputArray.length];
        String transformed;
        String original;
        int i = 0;
        for(Elements input: inputArray) {
            original = input.value();
            transformed = original;
            for(TransformerImpl current: input.transformers()) {
                Optional<AppConfig.TransformerData> transformer = AppConfig.transformerDatafromId(current.getTransformerId());
                if(transformer.isPresent()) {
                    try {
                        Class <?> classToGet = Class.forName(transformer.get().getClassname());
                        Constructor<?> cons = classToGet.getConstructor(String.class, String[].class);
                        Object obj = cons.newInstance(current.getTransformerId(), current.getParameters());
                        Method methodToInvoke = classToGet.getMethod("applyTransformer", String.class);
                        transformed = (String) methodToInvoke.invoke(obj, transformed);
                    } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | 
                        InvocationTargetException | ClassNotFoundException ex) {
                        throw new IllegalArgumentException("Error instantiating transformer");
                    }
                } else {
                    throw new IllegalArgumentException("Transformer ID not found");
                }
            }
            responses[i] = new ResponseElement(original, transformed);
            i++;
        }
        return responses;
    }
}

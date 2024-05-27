package com.transformers.demo.model.transformer;

public class Transformer1 extends TransformerImpl {

    public Transformer1(String transformerId, String[] parameters) {
        this.setTransformerId(transformerId);
        this.setParameters(parameters);
    }

    @Override
    public String applyTransformer(String value){
        return value.replaceAll(this.getParameters()[0], "");
    }
}

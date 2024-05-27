package com.transformers.demo.config;

import java.util.Optional;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.transformers.demo.service.ElementProcessor;

@Configuration
public class AppConfig {
    public enum TransformerData {
        T1("t1", "com.transformers.demo.model.transformer.Transformer1",  1),
        T2("t2", "com.transformers.demo.model.transformer.Transformer2", 2),
        T3("t3", "com.transformers.demo.model.transformer.Transformer3", 0);

        private final String id;
        private final String  className;
        private final int minParamNumber;

        TransformerData(String id, String className, int minParamNumber){
            this.id = id;
            this.className = className;
            this.minParamNumber = minParamNumber;
        }

        public String getId(){
            return this.id;
        }

        public String getClassname(){
            return this.className;
        }

        public int getMinParamNumber(){
            return this.minParamNumber;
        }
    }

    @Bean
    public ElementProcessor getProcessor() {
        return new ElementProcessor(this);
    }

    public static Optional<TransformerData> transformerDatafromId(String id) {
        for(TransformerData transformerData : TransformerData.values()) {
            if(transformerData.getId().equals(id)) {
                return Optional.of(transformerData);
            }
        }
        return Optional.empty();
    }
}

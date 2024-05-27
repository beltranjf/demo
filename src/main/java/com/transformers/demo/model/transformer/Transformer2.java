package com.transformers.demo.model.transformer;

public class Transformer2 extends TransformerImpl {
    public Transformer2(String transformerId, String[] parameters) {
        this.setTransformerId(transformerId);
        this.setParameters(parameters);
    }

    @Override
    public String applyTransformer(String value){
        return value.replaceAll(this.getParameters()[0], this.getParameters()[1]);
    }
}

package com.transformers.demo.model.transformer;

public class TransformerImpl implements Transformer{
    private String group;
    private String transformerId;
    private String[] parameters;

    public TransformerImpl(){}

    public String getGroup() {
        return this.group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getTransformerId() {
        return this.transformerId;
    }

    public void setTransformerId(String transformerId) {
        this.transformerId = transformerId;
    }

    public String[] getParameters() {
        return this.parameters;
    }

    public void setParameters(String[] parameters) {
        this.parameters = parameters;
    }

    public String applyTransformer(String value) {
        return value;
    }
}

package com.transformers.demo.model;

import com.transformers.demo.model.transformer.TransformerImpl;

public record Elements(String value, TransformerImpl[] transformers) {}

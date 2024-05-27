package com.transformers.demo.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.transformers.demo.config.AppConfig;
import com.transformers.demo.model.Input;
import com.transformers.demo.model.ResponseElement;
import com.transformers.demo.service.ElementProcessor;

@RestController
public class TransformerRestController {
    Logger logger = LoggerFactory.getLogger(TransformerRestController.class);
    private final AppConfig config;

    TransformerRestController(AppConfig config) {
        this.config = config;
    }

    @PostMapping(path = "/transform", 
    consumes = MediaType.APPLICATION_JSON_VALUE, 
    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseElement[]> transformValue(@RequestBody Input input) {
        try {
            ElementProcessor elementHandler = config.getProcessor();
            return ResponseEntity.ok().body(elementHandler.handleTransformations(input.elements()));
        } catch (IllegalArgumentException ex) {
            logger.error(ex.toString());
            ResponseElement response = new ResponseElement(ex.getMessage(), "");
            return ResponseEntity.badRequest().body(new ResponseElement[] { response });
        }
    }
}
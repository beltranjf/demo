package com.transformers.demo;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.transformers.demo.controller.TransformerRestController;

@SpringBootTest
class DemoApplicationTests {
	@Autowired
	private TransformerRestController transformerRestController;

	@Test
	void contextLoads() {
		assertNotNull(transformerRestController);
	}
}

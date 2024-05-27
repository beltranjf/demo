package com.transformers.demo.controller;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class TransformerRestControllerTest {
    String requestUrl = "http://localhost:%d/transform";
    static HttpHeaders headers;

    @LocalServerPort
	private int port;

	@Autowired
	private static TestRestTemplate restTemplate;

    @BeforeAll
    public static void runBeforeAllTestMethods() throws JSONException {
        restTemplate = new TestRestTemplate();
        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
    }

    @Test
	void simpleTransformShouldReturnOriginalAndTransformedValue() throws Exception {
        String originalText = "This is just some text!";
        JSONArray JSONParameters = new JSONArray();
        JSONParameters.put("!");

        JSONObject JSONTransformer = new JSONObject();
        JSONTransformer.put("group", "group1");
        JSONTransformer.put("transformerId", "t1");
        JSONTransformer.put("parameters",JSONParameters);

        JSONArray JSONTransformersArray = new JSONArray();
        JSONTransformersArray.put(JSONTransformer);

        JSONObject JSONElements = new JSONObject();
        JSONElements.put("value", originalText);
        JSONElements.put("transformers", JSONTransformersArray);

        JSONArray JSONElementsArray = new JSONArray();
        JSONElementsArray.put(JSONElements);

        JSONObject JSONBaseObject = new JSONObject();
        JSONBaseObject.put("elements", JSONElementsArray);

        HttpEntity<String> request = new HttpEntity<String>(JSONBaseObject.toString(), headers);
        String response = restTemplate.postForObject(String.format(requestUrl, port),request,String.class);
		assertTrue(response.contains("\"transformedValue\":\"This is just some text\""));
        assertTrue(response.contains(originalText));
	}

    @Test
	void complexTransformShouldTransliterate() throws Exception {
        String originalText = "This is just Βρίσκεστε στην αρχική σελίδα του: Δημόσιου!";

        JSONArray JSONParameters1 = new JSONArray();
        JSONParameters1.put("!");

        JSONObject JSONTransformer = new JSONObject();
        JSONTransformer.put("group", "group1");
        JSONTransformer.put("transformerId", "t1");
        JSONTransformer.put("parameters",JSONParameters1);

        JSONArray JSONParameters3 = new JSONArray();
        JSONObject JSONTransformer3 = new JSONObject();
        JSONTransformer3.put("group", "group1");
        JSONTransformer3.put("transformerId", "t3");
        JSONTransformer3.put("parameters",JSONParameters3);

        JSONArray JSONTransformersArray = new JSONArray();
        JSONTransformersArray.put(JSONTransformer);
        JSONTransformersArray.put(JSONTransformer3);

        JSONObject JSONElements = new JSONObject();
        JSONElements.put("value", originalText);
        JSONElements.put("transformers", JSONTransformersArray);

        JSONArray JSONElementsArray = new JSONArray();
        JSONElementsArray.put(JSONElements);

        JSONObject JSONBaseObject = new JSONObject();
        JSONBaseObject.put("elements", JSONElementsArray);

        HttpEntity<String> request = new HttpEntity<String>(JSONBaseObject.toString(), headers);
        String response = restTemplate.postForObject(String.format(requestUrl, port),request,String.class);
		assertTrue(response.contains("\"transformedValue\":\"This is just Vriskeste stin archiki selida tou: Dimosiou\""));
        assertTrue(response.contains(originalText));
	}
}

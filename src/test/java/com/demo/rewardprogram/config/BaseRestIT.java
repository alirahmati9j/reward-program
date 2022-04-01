package com.demo.rewardprogram.config;

import com.demo.rewardprogram.persistence.TestJpaUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestOperations;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

//@Component
public class BaseRestIT {

	@LocalServerPort
	private int port;

	@Autowired
	private MappingJackson2HttpMessageConverter messageConverter;

	@Autowired
	private Environment environment;

	@Autowired
	protected TestJpaUtils jpaUtils;

	@Autowired
	protected TestRestTemplate testRestTemplate;

	private RestOperations restTemplate;

	public RestOperations getRestTemplate() {
		return restTemplate;
	}

	public void setRestTemplate(RestOperations restTemplate) {
		this.restTemplate = restTemplate;
	}

	protected Environment getEnvironment() {
		return environment;
	}

	protected String formatUrl(String path) {
		return "http://localhost:" + port + path;
	}

	protected UriComponentsBuilder createUriBuilder(String path) {
		return UriComponentsBuilder.fromHttpUrl(formatUrl(path));
	}

	protected ObjectMapper getObjectMapper() {
		return messageConverter.getObjectMapper();
	}

	protected void assertStatus(ResponseEntity<String> entity, HttpStatus expectedStatus) {
		assertThat(entity.getStatusCode(), equalTo(expectedStatus));
	}

	protected <T> T assertStatus(ResponseEntity<String> entity, HttpStatus expectedStatus, Class<T> valueType) {
		assertThat(entity.getStatusCode(), equalTo(expectedStatus));
		return readJson(entity, valueType);
	}

	protected <T> T assertStatus(ResponseEntity<String> entity, HttpStatus expectedStatus, TypeReference<T> valueType) {
		assertThat(entity.getStatusCode(), equalTo(expectedStatus));
		return readJson(entity, valueType);
	}

	protected <T> T readJson(ResponseEntity<String> entity, Class<T> valueType) {
		return readJson(entity.getBody(), valueType);
	}

	protected <T> T readJson(ResponseEntity<String> entity, TypeReference<T> valueType) {
		return readJson(entity.getBody(), valueType);
	}

	protected <T> T readJson(String json, Class<T> valueType) {
		try {
			return getObjectMapper().readValue(json, valueType);
		} catch (IOException ex) {
			throw new HttpMessageNotReadableException("Could not read document: " + ex.getMessage(), ex);
		}
	}

	protected <T> T readJson(String json, TypeReference<T> valueType) {
		try {
			return getObjectMapper().readValue(json, valueType);
		} catch (IOException ex) {
			throw new HttpMessageNotReadableException("Could not read document: " + ex.getMessage(), ex);
		}
	}
}

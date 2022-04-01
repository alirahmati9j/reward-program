package com.ali.rewardprogram.config;

import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

//@Component
public class BaseRestOAuthAwareIT extends BaseRestIT {

	@LocalServerPort
    private int port;
	
	private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }

	@Override
	public RestOperations getRestTemplate() {
		return new RestTemplate();
	}

	@Override
	public void setRestTemplate(RestOperations restTemplate) {
		//this.restTemplate = restTemplate;
	}

	/**
	 * @param uri
	 * @return
	 */
	protected UriComponentsBuilder createUriComponent(String uri) {
		return UriComponentsBuilder.fromHttpUrl(createURLWithPort(uri));
	}

}


package com.baeldung.dddhexagonalspring.domain.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.client.RestTemplate;

public class DomainPostmanEchoService implements PostmanEchoService {

    private static final Logger log = LoggerFactory.getLogger(DomainPostmanEchoService.class);

    private final RestTemplate restTemplate;
    private static final String POSTMAN_ECHO_URL = "https://postman-echo.com";

    public DomainPostmanEchoService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Override
    @Cacheable(value = "get", key = "#request")
    public Object getEcho(String request) {
        log.info("connecting postman echo get => {}", request);
        String getUrl = String.format("%s/get?%s", POSTMAN_ECHO_URL, request);
        return restTemplate.getForObject(getUrl, Object.class);
    }

    @Override
    @Cacheable(value = "post", keyGenerator = "customKeyGenerator")
    public Object postEcho(String request, Object body) {
        log.info("connecting postman echo post => {}", body);
        String postUrl = String.format("%s/post%s", POSTMAN_ECHO_URL, request);
        return restTemplate.postForObject(postUrl, body, Object.class);
    }
}


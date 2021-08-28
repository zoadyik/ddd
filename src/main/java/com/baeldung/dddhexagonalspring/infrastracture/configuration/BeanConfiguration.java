package com.baeldung.dddhexagonalspring.infrastracture.configuration;

import com.baeldung.dddhexagonalspring.DomainLayerApplication;
import com.baeldung.dddhexagonalspring.domain.service.DomainPostmanEchoService;
import com.baeldung.dddhexagonalspring.domain.service.PostmanEchoService;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = DomainLayerApplication.class)
public class BeanConfiguration {

    @Bean
    PostmanEchoService orderService(RestTemplateBuilder restTemplateBuilder) {
        return new DomainPostmanEchoService(restTemplateBuilder);
    }

    @Bean("customKeyGenerator")
    public KeyGenerator keyGenerator() {
        return new CustomKeyGenerator();
    }
}

package com.juampi861.bankappdemo.bank.infra.restclient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.*;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestClientConfig {

    @Value("${bank-app.username}")
    private String username;

    @Value("${bank-app.password}")
    private String password;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplateBuilder()
                .basicAuthentication(username, password)
                .build();
    }
}

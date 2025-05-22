package com.juampi861.bankappdemo.bank.infra.restclient;

import com.juampi861.bankappdemo.bank.infra.rest.dto.BankResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class BankRestClient {

    private static final String BANKS_PATH = "/banks/";
    private final RestTemplate restTemplate;

    @Value("${bank-app.base-url}")
    private String baseUrl;

    public BankResponseDTO getBankById(String id) {
        String url = baseUrl + BANKS_PATH + id;
        return restTemplate.getForObject(url, BankResponseDTO.class);
    }
}

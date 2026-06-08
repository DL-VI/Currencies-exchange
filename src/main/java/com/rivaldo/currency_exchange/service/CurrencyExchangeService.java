package com.rivaldo.currency_exchange.service;

import com.rivaldo.currency_exchange.entity.ApiResponse;
import com.rivaldo.currency_exchange.entity.CurrencyDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class CurrencyExchangeService {
    @Value("${api.extern.secret-key}")
    private String secretKey;
    private final RestClient restClient;

    public CurrencyExchangeService(RestClient restClient) {
        this.restClient = restClient;
    }

    public List<CurrencyDto> supportedCurrencies() {
        ApiResponse response = restClient.get()
                .uri("/{key}/codes", secretKey)
                .retrieve()
                .body(ApiResponse.class);

        if (response == null || !"success".equals(response.result()))
            throw new RuntimeException("Error retrieving data from the currency API.");

        return response.supportedCodes().stream()
                .map(subList -> new CurrencyDto(
                        subList.getFirst(),
                        subList.getLast()))
                .toList();
    }

}

package com.rivaldo.currency_exchange.service;

import com.rivaldo.currency_exchange.entity.api_extern.ApiResponse;
import com.rivaldo.currency_exchange.entity.api_extern.ExchangeLatestApiResponse;
import com.rivaldo.currency_exchange.entity.api_extern.SupportedCodesApiResponse;
import com.rivaldo.currency_exchange.entity.CurrencyCodeDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Service
public class CurrencyExchangeService {
    @Value("${api.extern.secret-key}")
    private String secretKey;
    private final RestClient restClient;

    public CurrencyExchangeService(RestClient restClient) {
        this.restClient = restClient;
    }

    private void validateResponse(ApiResponse response)
    {
        if (response == null || !"success".equals(response.result()))
            throw new RuntimeException("Error retrieving data from the currency API.");
    }

    public List<CurrencyCodeDto> supportedCurrencies() {
        SupportedCodesApiResponse response = restClient.get()
                .uri("/{key}/codes", secretKey)
                .retrieve()
                .body(SupportedCodesApiResponse.class);

        validateResponse(response);

        return response.supportedCodes().stream()
                .map(subList -> new CurrencyCodeDto(
                        subList.getFirst(),
                        subList.getLast()))
                .toList();
    }

    public Map<String, BigDecimal> exchangeRate(String currency)
    {
        ExchangeLatestApiResponse response = restClient.get()
                .uri("/{key}/latest/{currency}", secretKey, currency)
                .retrieve()
                .body(ExchangeLatestApiResponse.class);

        validateResponse(response);

        return response.conversionRates();
    }
}

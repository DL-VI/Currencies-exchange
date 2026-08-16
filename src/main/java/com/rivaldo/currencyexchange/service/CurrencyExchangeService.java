package com.rivaldo.currencyexchange.service;

import com.rivaldo.currencyexchange.entity.api.ApiResponseStatus;
import com.rivaldo.currencyexchange.entity.api.ExchangeLatestApiResponseStatus;
import com.rivaldo.currencyexchange.entity.api.PairConversionApiResponseStatus;
import com.rivaldo.currencyexchange.entity.api.SupportedCodesApiResponseStatus;
import com.rivaldo.currencyexchange.entity.supportedcodes.CurrencyCodeDto;
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

    private void validateResponse(ApiResponseStatus response)
    {
        if (response == null || !"success".equals(response.result()))
            throw new RuntimeException("Error retrieving data from the currency API.");
    }

    public List<CurrencyCodeDto> supportedCurrencies() {
        SupportedCodesApiResponseStatus response = restClient.get()
                .uri("/{key}/codes", secretKey)
                .retrieve()
                .body(SupportedCodesApiResponseStatus.class);

        validateResponse(response);

        return response.supportedCodes().stream()
                .map(subList -> new CurrencyCodeDto(
                        subList.getFirst(),
                        subList.getLast()))
                .toList();
    }

    public Map<String, BigDecimal> exchangeRate(String currency)
    {
        ExchangeLatestApiResponseStatus response = restClient.get()
                .uri("/{key}/latest/{currency}", secretKey, currency)
                .retrieve()
                .body(ExchangeLatestApiResponseStatus.class);

        validateResponse(response);

        return response.conversionRates();
    }

    public BigDecimal pairConversion(BigDecimal amount , String from, String to)
    {
        PairConversionApiResponseStatus response = restClient.get()
                .uri("/{key}/pair/{from}/{to}", secretKey, from, to)
                .retrieve()
                .body(PairConversionApiResponseStatus.class);

        validateResponse(response);

        return amount.multiply(response.conversionRate());
    }
}

package com.rivaldo.currencyexchange.entity.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ExchangeLatestApiResponseStatus(
        String result,
        @JsonProperty("base_code") String baseCode,
        @JsonProperty("conversion_rates") Map<String, BigDecimal> conversionRates
        ) implements ApiResponseStatus {
}

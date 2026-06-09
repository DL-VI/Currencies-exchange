package com.rivaldo.currencyexchange.entity.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

@JsonIgnoreProperties(ignoreUnknown = true)
public record PairConversionApiResponseStatus(
        String result,
        @JsonProperty("time_last_update_utc") String timeLastUpdateUtc,
        @JsonProperty("base_code") String baseCode,
        @JsonProperty("target_code") String targetCode,
        @JsonProperty("conversion_rate") BigDecimal conversionRate
) implements ApiResponseStatus {
}

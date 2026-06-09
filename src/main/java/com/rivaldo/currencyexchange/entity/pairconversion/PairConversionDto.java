package com.rivaldo.currencyexchange.entity.pairconversion;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public record PairConversionDto(
        @JsonProperty("timeLastUpdateUtc") String time,
        @JsonProperty("base_code") String base,
        @JsonProperty("target_code") String target,
        @JsonProperty("conversion_rate") BigDecimal rate
) {
}

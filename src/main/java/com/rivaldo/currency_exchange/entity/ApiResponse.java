package com.rivaldo.currency_exchange.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ApiResponse(
        String result,
        @JsonProperty("supported_codes") List<List<String>> supportedCodes
) {
}

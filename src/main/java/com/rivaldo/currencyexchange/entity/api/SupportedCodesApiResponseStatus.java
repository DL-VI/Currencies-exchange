package com.rivaldo.currencyexchange.entity.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record SupportedCodesApiResponseStatus(
        String result,
        @JsonProperty("supported_codes") List<List<String>> supportedCodes
) implements ApiResponseStatus {
}

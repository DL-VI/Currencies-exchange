package com.rivaldo.currency_exchange.entity.api_extern;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record SupportedCodesApiResponse(
        String result,
        @JsonProperty("supported_codes") List<List<String>> supportedCodes
) implements ApiResponse {
}

package com.rivaldo.currencyexchange.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ApiResponse<T>(
        boolean success,
        String message,
        T data,
        String error
) {

    public static <T> ApiResponse<T> ok(String message, T data)
    {
        return new ApiResponse<>(true, message, data, null);
    }
}

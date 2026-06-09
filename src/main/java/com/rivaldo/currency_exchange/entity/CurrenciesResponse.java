package com.rivaldo.currency_exchange.entity;

import java.util.List;

public record CurrenciesResponse(
        List<CurrencyCodeDto> currencies,
        int totalCurrencies
) {
}

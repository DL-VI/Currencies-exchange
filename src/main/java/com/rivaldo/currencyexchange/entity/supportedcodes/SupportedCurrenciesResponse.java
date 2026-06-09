package com.rivaldo.currencyexchange.entity.supportedcodes;

import java.util.List;

public record SupportedCurrenciesResponse(
        List<CurrencyCodeDto> currencies,
        int totalCurrencies
) {
}

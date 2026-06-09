package com.rivaldo.currency_exchange.controller;

import com.rivaldo.currency_exchange.entity.CurrenciesResponse;
import com.rivaldo.currency_exchange.service.CurrencyExchangeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/${api.extern.secret-key}")
public class CurrenciesController {
    private final CurrencyExchangeService service;

    public CurrenciesController(CurrencyExchangeService service) {
        this.service = service;
    }

    @GetMapping("/currencies")
    public ResponseEntity<CurrenciesResponse> getSupportedCurrencies()
    {
        var response = service.supportedCurrencies();
        return ResponseEntity.ok(new CurrenciesResponse(
                response,
                response.size()
        ));
    }

    @GetMapping("/rates/{currency}")
    public ResponseEntity<Map<String, BigDecimal>> getExchangeRates(@PathVariable String currency)
    {
        return ResponseEntity.ok(service.exchangeRate(currency.toUpperCase()));
    }
}

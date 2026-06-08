package com.rivaldo.currency_exchange.controller;

import com.rivaldo.currency_exchange.entity.CurrencyDto;
import com.rivaldo.currency_exchange.service.CurrencyExchangeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/${api.extern.secret-key}")
public class CurrenciesController {
    private final CurrencyExchangeService service;

    public CurrenciesController(CurrencyExchangeService service) {
        this.service = service;
    }

    @GetMapping("/currencies")
    public ResponseEntity<List<CurrencyDto>> getSupportedCurrencies() {
        return ResponseEntity.ok(service.supportedCurrencies());
    }
}

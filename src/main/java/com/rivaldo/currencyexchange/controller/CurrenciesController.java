package com.rivaldo.currencyexchange.controller;

import com.rivaldo.currencyexchange.entity.ApiResponse;
import com.rivaldo.currencyexchange.entity.supportedcodes.CurrencyCodeDto;
import com.rivaldo.currencyexchange.service.CurrencyExchangeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/${api.extern.secret-key}")
public class CurrenciesController {
    private final CurrencyExchangeService service;

    public CurrenciesController(CurrencyExchangeService service) {this.service = service;}

    @GetMapping("/currencies")
    public ResponseEntity<ApiResponse<List<CurrencyCodeDto>>> getSupportedCurrencies() {
        var response = service.supportedCurrencies();
        return ResponseEntity.ok(ApiResponse.ok(
                "Supported codes",
                response
        ));
    }

    @GetMapping("/rates/{currency}")
    public ResponseEntity<ApiResponse<Map<String, BigDecimal>>> getExchangeRates(@PathVariable String currency) {
        var response = service.exchangeRate(currency.toUpperCase());
        return ResponseEntity.ok(ApiResponse.ok(
                "Exchange rate",
                response
        ));
    }

    @GetMapping("/pair")
    public ResponseEntity<BigDecimal> getPairConversion(@RequestParam BigDecimal amount,
                                                        @RequestParam String from,
                                                        @RequestParam String to) {
        return ResponseEntity.ok(service.pairConversion(amount,from,to));
    }
}

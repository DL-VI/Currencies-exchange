package com.rivaldo.currencyexchange.controller;

import com.rivaldo.currencyexchange.entity.ApiResponse;
import com.rivaldo.currencyexchange.entity.pairconversion.PairConversionDto;
import com.rivaldo.currencyexchange.entity.supportedcodes.CurrencyCodeDto;
import com.rivaldo.currencyexchange.service.CurrencyExchangeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/${api.extern.secret-key}")
public class CurrenciesController {
    private final CurrencyExchangeService service;

    public CurrenciesController(CurrencyExchangeService service) {
        this.service = service;
    }

    @GetMapping("/currencies")
    public ResponseEntity<ApiResponse<List<CurrencyCodeDto>>> getSupportedCurrencies()
    {
        var response = service.supportedCurrencies();
        return ResponseEntity.ok(ApiResponse.ok(
                "Supported codes",
                response
        ));
    }

    @GetMapping("/rates/{currency}")
    public ResponseEntity<ApiResponse<Map<String, BigDecimal>>> getExchangeRates(@PathVariable String currency)
    {
        var response = service.exchangeRate(currency.toUpperCase());
        return ResponseEntity.ok(ApiResponse.ok(
                "Exchange rate",
                response
        ));
    }

    @GetMapping("/pair/{base}/{target}")
    public ResponseEntity<ApiResponse<PairConversionDto>> getPairConversion(@PathVariable String base, @PathVariable String target)
    {
        var response = service.pairConversion(base, target);
        return ResponseEntity.ok(ApiResponse.ok(
                "Pair conversion",
                response
        ));
    }
}

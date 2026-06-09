package com.rivaldo.currencyexchange.controller;

import com.rivaldo.currencyexchange.entity.pairconversion.PairConversionDto;
import com.rivaldo.currencyexchange.entity.supportedcodes.SupportedCurrenciesResponse;
import com.rivaldo.currencyexchange.service.CurrencyExchangeService;
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
    public ResponseEntity<SupportedCurrenciesResponse> getSupportedCurrencies()
    {
        var response = service.supportedCurrencies();
        return ResponseEntity.ok(new SupportedCurrenciesResponse(
                response,
                response.size()
        ));
    }

    @GetMapping("/rates/{currency}")
    public ResponseEntity<Map<String, BigDecimal>> getExchangeRates(@PathVariable String currency)
    {
        return ResponseEntity.ok(service.exchangeRate(currency.toUpperCase()));
    }

    @GetMapping("/pair/{base}/{target}")
    public ResponseEntity<PairConversionDto> getPairConversion(@PathVariable String base, @PathVariable String target)
    {
        return ResponseEntity.ok(service.pairConversion(base, target));
    }

}

package com.ty.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.ty.model.CurrencyConversionRequest;
import com.ty.service.CurrencyService;

@RestController
@RequestMapping("/api")
public class CurrencyController {
    private final CurrencyService currencyService;

    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @GetMapping("/rates")
    public ResponseEntity<?> fetchRates(@RequestParam(defaultValue = "USD") String base) {
        return currencyService.getExchangeRates(base);
    }

    @PostMapping("/convert")
    public ResponseEntity<?> convert(@RequestBody CurrencyConversionRequest request) {
        return currencyService.convertCurrency(request);
    }
}

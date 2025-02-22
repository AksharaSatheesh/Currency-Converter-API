package com.ty.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.ty.model.CurrencyConversionRequest;

import java.util.Map;

@Service
public class CurrencyService {
    private final RestTemplate restTemplate = new RestTemplate();
    private static final String API_URL = "https://api.exchangerate-api.com/v4/latest/";

    public ResponseEntity<?> getExchangeRates(String base) {
        try {
            String url = API_URL + base;
            Map<String, Object> response = restTemplate.getForObject(url, Map.class);
            
            if (response == null || !response.containsKey("rates")) {
                return ResponseEntity.badRequest().body(Map.of("error", "Invalid base currency. Please check the currency code."));
            }
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", "Failed to fetch exchange rates. Please check your input."));
        }
    }

    public ResponseEntity<?> convertCurrency(CurrencyConversionRequest request) {
        try {
            ResponseEntity<?> responseEntity = getExchangeRates(request.getFrom());
            
            if (!(responseEntity.getBody() instanceof Map<?, ?> response) || !response.containsKey("rates")) {
                return ResponseEntity.badRequest().body(Map.of("error", "Invalid base currency."));
            }

            Map<String, Double> rateMap = (Map<String, Double>) response.get("rates");

            if (!rateMap.containsKey(request.getTo())) {
                return ResponseEntity.badRequest().body(Map.of("error", "Invalid target currency."));
            }

            double convertedAmount = Math.round(request.getAmount() * rateMap.get(request.getTo()) * 100.0) / 100.0;
            return ResponseEntity.ok(Map.of(
                "from", request.getFrom(),
                "to", request.getTo(),
                "amount", request.getAmount(),
                "convertedAmount", convertedAmount
            ));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", "Currency conversion failed. Please check your input and try again."));
        }
    }
}

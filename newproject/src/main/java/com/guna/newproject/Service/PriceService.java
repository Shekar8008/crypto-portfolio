package com.guna.newproject.Service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class PriceService {

    private final RestTemplate restTemplate = new RestTemplate();

    // Map symbol to CoinGecko ID
    private String mapToCoinGeckoId(String symbol) {
        switch (symbol.toUpperCase()) {
            case "BTC": return "bitcoin";
            case "ETH": return "ethereum";
            case "SOL": return "solana";
            case "ADA": return "cardano";
            case "TON": return "the-open-network";
            case  "NS": return "suins-token";
            case "TRUMP": return "official-trump";
            case "SHIB": return "shiba-inu";
            case "XRP": return "ripple";
            case "DOGE": return "dogecoin";   
            // add more mappings as needed
            default: return null;
        }
    }

    public String mapToCoinName(String symbol) {
        switch (symbol.toUpperCase()) {
            case "BTC": return "Bitcoin";
            case "ETH": return "Ethereum";
            case "SOL": return "Cardano";
            case "ADA": return "Cardano";
            case "TON": return "Toncoin";
            case  "NS": return "Suins";
            case "TRUMP": return "Trump";
            case "SHIB": return "SHIBHA";
            case "XRP": return "Xrp";
            case "DOGE": return "Dogecoin";   
            default: return symbol;
        }
    }

    // Single coin price
    public BigDecimal getLivePrice(String symbol, String vsCurrency) {
        String id = mapToCoinGeckoId(symbol);
        if (id == null) return BigDecimal.ZERO;

        String url = String.format(
            "https://api.coingecko.com/api/v3/simple/price?ids=%s&vs_currencies=%s",
            id, vsCurrency.toLowerCase()
        );

        try {
            Map<String, Map<String, Number>> response = restTemplate.getForObject(url, Map.class);
            if (response == null || !response.containsKey(id)) return BigDecimal.ZERO;

            Number price = response.get(id).get(vsCurrency.toLowerCase());
            return price == null ? BigDecimal.ZERO : BigDecimal.valueOf(price.doubleValue());
        } catch (Exception e) {
            System.out.println("Error fetching price for " + symbol + ": " + e.getMessage());
            return BigDecimal.ZERO;
        }
    }

    // Batch prices
    public Map<String, BigDecimal> getLivePrices(List<String> symbols, String vsCurrency) {
        List<String> ids = symbols.stream()
                .map(this::mapToCoinGeckoId)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        if (ids.isEmpty()) return Map.of();

        String idsParam = String.join(",", ids);
        String url = String.format(
            "https://api.coingecko.com/api/v3/simple/price?ids=%s&vs_currencies=%s",
            idsParam, vsCurrency.toLowerCase()
        );

        try {
            Map<String, Map<String, Number>> response = restTemplate.getForObject(url, Map.class);
            if (response == null) return Map.of();

            return symbols.stream().collect(Collectors.toMap(
                s -> s,
                s -> {
                    String id = mapToCoinGeckoId(s);
                    if (id == null || !response.containsKey(id)) return BigDecimal.ZERO;
                    Number price = response.get(id).get(vsCurrency.toLowerCase());
                    return price == null ? BigDecimal.ZERO : BigDecimal.valueOf(price.doubleValue());
                }
            ));
        } catch (Exception e) {
            System.out.println("Batch price fetch error: " + e.getMessage());
            return Map.of();
        }
    }
}
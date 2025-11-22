package com.guna.newproject.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guna.newproject.Entity.HoldingEntity;
import com.guna.newproject.HoldingsDTO.HoldingDTO;
import com.guna.newproject.Repository.HoldingRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PortfolioService {

    @Autowired
    private PriceService priceService;

    @Autowired
    private HoldingRepository holdingRepository;

    public BigDecimal calculateTotalValue(String vsCurrency) {
        List<HoldingEntity> holdings = holdingRepository.findAll();
        BigDecimal total = BigDecimal.ZERO;

        List<String> symbols = holdings.stream()
                .map(HoldingEntity::getSymbol)
                .collect(Collectors.toList());

        Map<String, BigDecimal> priceMap = priceService.getLivePrices(symbols, vsCurrency);

        for (HoldingEntity holding : holdings) {
            if (holding != null && holding.getSymbol() != null && holding.getQuantity() != null) {
                BigDecimal price = priceMap.getOrDefault(holding.getSymbol(), BigDecimal.ZERO);
                BigDecimal currentValue = price.multiply(holding.getQuantity());
                total = total.add(currentValue);
            }
        }
        return total;
    }

    public List<HoldingDTO> getHoldingsWithValues(String vsCurrency) {
        List<HoldingEntity> holdings = holdingRepository.findAll();

        List<String> symbols = holdings.stream()
                .map(HoldingEntity::getSymbol)
                .collect(Collectors.toList());

        Map<String, BigDecimal> priceMap = priceService.getLivePrices(symbols, vsCurrency);

        return holdings.stream().map(h -> {
            BigDecimal buyValue = h.getBuyPrice().multiply(h.getQuantity());
            BigDecimal livePrice = priceMap.getOrDefault(h.getSymbol(), BigDecimal.ZERO);
            BigDecimal marketValue = livePrice.multiply(h.getQuantity());
            BigDecimal profitLoss = marketValue.subtract(buyValue);

            HoldingDTO dto = new HoldingDTO();
            dto.setSymbol(h.getSymbol());
            dto.setName(priceService.mapToCoinName(h.getSymbol()));
            dto.setQuantity(h.getQuantity());
            dto.setBuyPrice(h.getBuyPrice());
            dto.setBuyValue(buyValue);
            dto.setLivePrice(livePrice);
            dto.setMarketValue(marketValue);
            dto.setProfitLoss(profitLoss);

            return dto;
        }).collect(Collectors.toList());
    }
}


package com.guna.newproject.HoldingsDTO;


import java.math.BigDecimal;

public class HoldingDTO {
    private String symbol;
    private String name;
    private BigDecimal quantity;
    private BigDecimal buyPrice;
    private BigDecimal buyValue;
    private BigDecimal livePrice;
    private BigDecimal marketValue;
    private BigDecimal profitLoss;

    // Getters and setters
    public String getSymbol() { return symbol; }
    public void setSymbol(String symbol) { this.symbol = symbol; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public BigDecimal getQuantity() { return quantity; }
    public void setQuantity(BigDecimal quantity) { this.quantity = quantity; }

    public BigDecimal getBuyPrice() { return buyPrice; }
    public void setBuyPrice(BigDecimal buyPrice) { this.buyPrice = buyPrice; }

    public BigDecimal getBuyValue() { return buyValue; }
    public void setBuyValue(BigDecimal buyValue) { this.buyValue = buyValue; }

    public BigDecimal getLivePrice() { return livePrice; }
    public void setLivePrice(BigDecimal livePrice) { this.livePrice = livePrice; }

    public BigDecimal getMarketValue() { return marketValue; }
    public void setMarketValue(BigDecimal marketValue) { this.marketValue = marketValue; }

    public BigDecimal getProfitLoss() { return profitLoss; }
    public void setProfitLoss(BigDecimal profitLoss) { this.profitLoss = profitLoss; }
}



package com.guna.newproject.Entity;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;

@Entity
public class HoldingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String symbol;
    private String name;
    private BigDecimal quantity;
    private BigDecimal buyPrice;

    @Transient
    private BigDecimal currentPrice;

    @Transient
    private BigDecimal totalValue;

    // ✅ Default constructor required by JPA
    public HoldingEntity() {}

    // ✅ Parameterized constructor
    public HoldingEntity(String symbol, String name, double quantity, double buyPrice) {
        this.symbol = symbol;
        this.name = name;
        this.quantity = BigDecimal.valueOf(quantity);
        this.buyPrice = BigDecimal.valueOf(buyPrice);
    }

    public Long getId() {
        return id;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public BigDecimal getBuyPrice() {
        return buyPrice;
    }
}



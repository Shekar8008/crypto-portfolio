package com.guna.newproject.Controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.guna.newproject.HoldingsDTO.HoldingDTO;
import com.guna.newproject.Service.PortfolioService;

@RestController
@RequestMapping("/api/holdings")
public class PortfolioController {

    @Autowired
    private PortfolioService portfolioService;

    @GetMapping("/list")
    public List<HoldingDTO> getAllHoldings(@RequestParam(defaultValue = "usd") String vs) {
        return portfolioService.getHoldingsWithValues(vs);
    }

    @GetMapping("/value")
    public BigDecimal getPortfolioValue(@RequestParam(defaultValue = "usd") String vs) {
        return portfolioService.calculateTotalValue(vs);
    }

      
}

package com.guna.newproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.guna.newproject.Entity.HoldingEntity;
import com.guna.newproject.Repository.HoldingRepository;

@SpringBootApplication
@Configuration
public class CryptoPortfolioApplication {

	public static void main(String[] args) {
		SpringApplication.run(CryptoPortfolioApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(HoldingRepository holdingRepository) {
		return args -> {
			holdingRepository.save(new HoldingEntity("BTC", "Bitcoin",   1, 100000));
			holdingRepository.save(new HoldingEntity("ETH", "Ethereum",  1, 4000));
			holdingRepository.save(new HoldingEntity("SOL", "Solana",    10, 100));
			holdingRepository.save(new HoldingEntity("ADA", "Cardano",   30, 0.35));
			holdingRepository.save(new HoldingEntity("TON", "Tonecoin",  10, 3));
			holdingRepository.save(new HoldingEntity("DOGE", "Doge",     10, 0.2));
			holdingRepository.save(new HoldingEntity("NS",   "Suins" ,   1000, 0.1));
			holdingRepository.save(new HoldingEntity("TRUMP", "Trump",   1, 10));
			holdingRepository.save(new HoldingEntity("SHIB",  "Shibha"  ,1,10));
			holdingRepository.save(new HoldingEntity("XRP",    "Xrp",    1,10));
		};
	}
}

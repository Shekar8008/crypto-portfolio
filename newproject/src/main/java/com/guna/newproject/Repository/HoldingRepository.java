package com.guna.newproject.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.guna.newproject.Entity.HoldingEntity;

public interface HoldingRepository extends JpaRepository<HoldingEntity, Long> {
   
}
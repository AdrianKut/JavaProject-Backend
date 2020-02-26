package com.DTeam.eshop.repositories;

import com.DTeam.eshop.entities.Order;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
    
}
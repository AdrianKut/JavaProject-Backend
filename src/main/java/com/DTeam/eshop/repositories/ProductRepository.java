package com.DTeam.eshop.repositories;

import java.util.List;

import com.DTeam.eshop.entities.Product;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByOrdersOrderId(Long orderId);
}
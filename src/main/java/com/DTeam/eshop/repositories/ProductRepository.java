package com.DTeam.eshop.repositories;

import com.DTeam.eshop.entities.Product;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    
}
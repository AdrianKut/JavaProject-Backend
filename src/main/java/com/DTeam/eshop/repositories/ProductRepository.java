package com.DTeam.eshop.repositories;

import java.util.List;

import com.DTeam.eshop.entities.Product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByOrdersOrderId(Long orderId);
    Page<Product> findByCategory(String name, Pageable pageable);
    List<Product> findByNameContainingIgnoreCaseOrCategoryContainingIgnoreCase(String name, String catergory);
    List<Product> findByQuantityLessThan(Integer quantity);
}
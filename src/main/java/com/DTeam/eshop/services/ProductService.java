package com.DTeam.eshop.services;

import java.util.List;

import com.DTeam.eshop.entities.Product;
import com.DTeam.eshop.repositories.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
public class ProductService {

    @Autowired private ProductRepository productRepository;

    public List<Product> listAll(){
        return productRepository.findAll();
    }

    public Product save(Product product){
        return productRepository.save(product);
    }

    public Product get(Long id){
        return productRepository.findById(id).get();
    }

    public Product delete(Long id){
        Product product = get(id);
        productRepository.deleteById(id);
        return product;
    }

    public Boolean isProductExist(Long id){
        return productRepository.existsById(id);
    }

    public List<Product> getByOrderId(Long orderId){
        return productRepository.findByOrdersOrderId(orderId);
    }

    public List<Product> getByNameOrCategory(String name){
        return productRepository.findByNameContainingIgnoreCaseOrCategoryContainingIgnoreCase(name, name);
    }

    public Page<Product> getByCategory(String category, Pageable pageable){
        return productRepository.findByCategory(category, pageable);
    }

    public List<Product> getProductsEnding(){
        return productRepository.findByQuantityLessThanOrderByQuantityAsc(5);
    }
}
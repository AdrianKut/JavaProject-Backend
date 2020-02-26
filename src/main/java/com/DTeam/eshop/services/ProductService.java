package com.DTeam.eshop.services;

import java.util.List;

import com.DTeam.eshop.entities.Product;
import com.DTeam.eshop.repositories.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired private ProductRepository productRepository;
    
    public List<Product> listAll(){
        return productRepository.findAll();
    }

    public void save(Product product){
        productRepository.save(product);
    }

    public Product get(Long id){
        return productRepository.findById(id).get();
    }

    public void delete(Long id){
        productRepository.deleteById(id);
    }
}
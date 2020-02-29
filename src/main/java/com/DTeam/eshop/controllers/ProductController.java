package com.DTeam.eshop.controllers;

import java.util.List;

import com.DTeam.eshop.entities.Product;
import com.DTeam.eshop.services.ProductService;
import com.DTeam.eshop.utilities.CustomErrorType;
import org.springframework.http.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired private ProductService productService;

    //Retrieve all products
    @GetMapping("/products")
    public ResponseEntity<List<Product>> getProducts() {
        List<Product> products = productService.listAll();
        if(products.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
    }

    //Retrieve single product
    @GetMapping("/products/{id}")
    public ResponseEntity<?> getProduct(@PathVariable("id")Long productId){
        if(productService.isProductExist(productId)){
            Product product = productService.get(productId);
            return new ResponseEntity<Product>(product, HttpStatus.OK);
        }
        return new ResponseEntity<>(new CustomErrorType("Product with id " + productId + " not found."), HttpStatus.NOT_FOUND);
    }

     //Create a product
     @PostMapping("/products")
     public ResponseEntity<?> createProduct(@RequestBody Product product, UriComponentsBuilder ucBuilder) {
         Long id = product.getProductId();
         if(id != null){
             return new ResponseEntity<>(new CustomErrorType("Unable to create. A product with id " + id + 
             " already exist."), HttpStatus.CONFLICT);
         }
         productService.save(product);
 
         HttpHeaders headers = new HttpHeaders();
         headers.setLocation(ucBuilder.path("/api/products/{id}").buildAndExpand(product.getProductId()).toUri());
         return new ResponseEntity<String>(headers, HttpStatus.CREATED);
     }

       //Update a product
    @PutMapping("/products/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable("id")Long productId, @RequestBody Product product){
        if(productService.isProductExist(productId)){
            Product currentProduct = productService.get(productId);
            currentProduct.setName(product.getName());
            currentProduct.setDescription(product.getDescription());
            currentProduct.setPrice(product.getPrice());
            currentProduct.setQuantity(product.getQuantity());
            productService.save(currentProduct);

            return new ResponseEntity<Product>(currentProduct, HttpStatus.OK);
        }

        return new ResponseEntity<>(new CustomErrorType("Unable to update. Product with id " + productId + " not found."),
        HttpStatus.NOT_FOUND);
    }

    //Delete a product
    @DeleteMapping("/products/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable("id")Long productId){
        if(productService.isProductExist(productId)){
            productService.delete(productId);
            return new ResponseEntity<Product>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(new CustomErrorType("Unable to delete. Product with id " + productId + " not found."),
        HttpStatus.NOT_FOUND);
    }

}
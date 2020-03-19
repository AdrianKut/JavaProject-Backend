package com.DTeam.eshop.utilities;

import java.util.ArrayList;
import java.util.List;

import com.DTeam.eshop.entities.Product;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.context.annotation.ScopedProxyMode;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ShoppingCart {

    private List<Product> products = new ArrayList<Product>();

    public void add(Product product){
        products.add(product);
    }

    public void delete(int index){
        products.remove(index);
    }

    public List<Product> get(){
        return this.products;
    }

    public Double getAmount(){
        Double sum = 0.0;
        for (Product product : products) {
            sum += product.getPrice();
        }
        return sum;
    }
}
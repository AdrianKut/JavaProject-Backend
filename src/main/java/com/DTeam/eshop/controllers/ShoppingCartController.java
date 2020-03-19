package com.DTeam.eshop.controllers;

import java.util.List;

import com.DTeam.eshop.entities.Product;
import com.DTeam.eshop.services.ProductService;
import com.DTeam.eshop.utilities.ShoppingCart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ShoppingCartController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ShoppingCart shoppingCart;

    @GetMapping("/shopping-cart")
    public String getFromCart(Model model){
        List<Product> productList = shoppingCart.get();
        Double amount = shoppingCart.getAmount();
        model.addAttribute("amount", amount);
        model.addAttribute("productList", productList);
        return "/views/shopping-cart";
    }

    @GetMapping("/shopping-cart/add/{id}")
    public String addToCart(@PathVariable(name = "id")Long id){
        Product product = productService.get(id);
        shoppingCart.add(product);
        return "redirect:/";
    }

    @GetMapping("/shopping-cart/delete/{id}")
    public String deleteFromCart(@PathVariable(name = "id")int id){
        shoppingCart.delete(id);
        return "redirect:/shopping-cart";
    }
}
package com.DTeam.eshop.controllers.employee;

import java.util.List;

import com.DTeam.eshop.entities.Product;
import com.DTeam.eshop.services.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class EmployeeProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/employee/product-list")
    public String getAll(Model model){
        List<Product> productList = productService.listAll();
        model.addAttribute("productList",productList);
        return "views/employee/listProduct";
    }

    @GetMapping("/employee/product-edit/{id}")
    public String edit(Model model,@PathVariable(name = "id") long id){
        Product product = productService.get(id);
        model.addAttribute("product", product);
        return "views/employee/editProduct";
    }

    @PostMapping("/employee/product-edit/{id}")
    public String edit(@PathVariable(name = "id") long id,
    Product product){
        Product currnetProduct = productService.get(id);
        currnetProduct.setQuantity(product.getQuantity());
        productService.save(currnetProduct);
        return "redirect:/employee/product-list";
    }
}
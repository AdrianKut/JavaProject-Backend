package com.DTeam.eshop.controllers;

import java.util.List;

import com.DTeam.eshop.entities.Product;
import com.DTeam.eshop.services.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ProductController {
    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/product/list", method = RequestMethod.GET)
    public String getAll(Model model){
        List<Product> productList = productService.listAll();
        model.addAttribute("productList",productList);
        return "/views/product/list";
    }

    @RequestMapping(value = "/product/add", method = RequestMethod.GET)
    public String save(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);
        return "/views/product/add";
    }

    @RequestMapping(value = "/product/add", method = RequestMethod.POST)
    public String save(Product product){
         
        productService.save(product);
        return "redirect:/product/list";
    }

    @RequestMapping(value = "/product/edit/{id}", method = RequestMethod.GET)
    public String edit(Model model,@PathVariable(name = "id") long id){
        
        Product product = productService.get(id);
        model.addAttribute("product", product);
        return "/views/product/edit";
    }
    
    @RequestMapping(value = "/product/edit/{id}", method = RequestMethod.POST)
    public String edit(@PathVariable(name = "id") long id, 
    Product product){ 
       
        product.setProductId(id);
        productService.save(product);
        return "redirect:/product/list";
    }

    @RequestMapping(value = "/product/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable(name = "id") long id){
        productService.delete(id);
        return "redirect:/product/list";
    }
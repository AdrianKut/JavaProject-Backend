package com.DTeam.eshop.controllers.admin;

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
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/admin/product/list")
    public String getAll(Model model){
        List<Product> productList = productService.listAll();
        model.addAttribute("productList",productList);
        return "views/admin/product/list";
    }

    @GetMapping("/admin/product/add")
    public String save(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);
        return "views/admin/product/add";
    }

    @PostMapping("/admin/product/add")
    public String save(Product product){
        productService.save(product);
        return "redirect:/admin/product/list";
    }

    @GetMapping("/admin/product/edit/{id}")
    public String edit(Model model,@PathVariable(name = "id") long id){
        Product product = productService.get(id);
        model.addAttribute("product", product);
        return "views/admin/product/edit";
    }

    @PostMapping("/admin/product/edit/{id}")
    public String edit(@PathVariable(name = "id") long id,
    Product product){
        product.setProductId(id);
        productService.save(product);
        return "redirect:/admin/product/list";
    }

    @GetMapping("/admin/product/delete/{id}")
    public String delete(@PathVariable(name = "id") long id){
        productService.delete(id);
        return "redirect:/admin/product/list";
    }
}
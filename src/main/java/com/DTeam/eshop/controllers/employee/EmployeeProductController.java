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

/**
 * Klasa obsługuje Produkt-Pracownik
 * @author User
 */
@Controller
public class EmployeeProductController {

    @Autowired
    private ProductService productService;

    /**
     * Metoda wyświetla liste Produktów
     * @param model przechowywanie atrybutów modelu
     * @return widok listy produktów
     */
    @GetMapping("/employee/product-list")
    public String getAll(Model model){
        List<Product> productList = productService.listAll();
        model.addAttribute("productList",productList);
        return "views/employee/listProduct";
    }

    /**
     * Metoda edytuje Produkt
     * @param model przechowywanie atrybutów modelu
     * @param id przechowuje id Produktu
     * @return widok edycji formularza Produktu
     */
    @GetMapping("/employee/product-edit/{id}")
    public String edit(Model model,@PathVariable(name = "id") long id){
        Product product = productService.get(id);
        model.addAttribute("product", product);
        return "views/employee/editProduct";
    }

    /**
     * Metoda edytuje Produkt
     * @param id przechowuje id Produktu
     * @param product przechowuje Dane produktu
     * @return widok listy produktów
     */
    @PostMapping("/employee/product-edit/{id}")
    public String edit(@PathVariable(name = "id") long id,
    Product product){
        Product currnetProduct = productService.get(id);
        currnetProduct.setQuantity(product.getQuantity());
        productService.save(currnetProduct);
        return "redirect:/employee/product-list";
    }
}
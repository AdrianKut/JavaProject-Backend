package com.DTeam.eshop.controllers.admin;

import java.util.List;

import javax.validation.Valid;

import com.DTeam.eshop.entities.Product;
import com.DTeam.eshop.services.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * Klasa obsługująca Produkt
 * @author 
 */
@Controller
public class ProductController {
    @Autowired
    private ProductService productService;

    /**
     * Metoda wyswietlająca liste produktów
     * @param model przechowywanie atrybutów modelu
     * @return widok listy Produktów
     */
    @GetMapping("/admin/product/list")
    public String getAll(Model model){
        List<Product> productList = productService.listAll();
        model.addAttribute("productList",productList);
        return "views/admin/product/list";
    }

    /**
     * Metoda dodająca Produkt
     * @param model przechowywanie atrybutów modelu
     * @return widok formularza Produktów
     */
    @GetMapping("/admin/product/add")
    public String save(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);
        return "views/admin/product/add";
    }

    /**
     * Metoda dodająca Produkt
     * @param product przechowuje Dane Produktu
     * @param bindingResult walidacja błędów
     * @return widok listy Produktów
     */
    @PostMapping("/admin/product/add")
    public String save(@Valid Product product, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "views/admin/product/add";
        }
        productService.save(product);
        return "redirect:/admin/product/list";
    }

    /**
     * Metoda edycji Produktu
     * @param model przechowywanie atrybutów modelu
     * @param id przechowuje Id Produktu
     * @return widok edycji formularza Produktu
     */
    @GetMapping("/admin/product/edit/{id}")
    public String edit(Model model,@PathVariable(name = "id") long id){
        Product product = productService.get(id);
        model.addAttribute("product", product);
        return "views/admin/product/edit";
    }

    /**
     * Metoda edycji Produktu
     * @param id przechowuje Id Produktu
     * @param product przechowuje Dane Produktu
     * @param bindingResult walidacja błędów
     * @return widok listy Produktów
     */
    @PostMapping("/admin/product/edit/{id}")
    public String edit(@PathVariable(name = "id") long id,
    @Valid Product product, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "views/admin/product/edit";
        }
        product.setProductId(id);
        productService.save(product);
        return "redirect:/admin/product/list";
    }

    /**
     * Metoda usuwania Produktu
     * @param id przechowuje Id Produktu
     * @return widok listy Produktów
     */
    @GetMapping("/admin/product/delete/{id}")
    public String delete(@PathVariable(name = "id") long id){
        productService.delete(id);
        return "redirect:/admin/product/list";
    }
}
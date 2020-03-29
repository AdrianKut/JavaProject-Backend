package com.DTeam.eshop.controllers;

import java.util.List;

import com.DTeam.eshop.entities.Product;
import com.DTeam.eshop.services.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpServletRequest;
import org.springframework.data.domain.PageRequest;

/**
 * Klasa dzieląca Produkty na
 * kategorie
 * @author 
 */
@Controller
public class ProductCategoryController {

    @Autowired
    private ProductService productService;

    /**
     * Metoda dzieląca Produkty na
     * kategorie "Laptopy i Komputery"
     * @param model przechowywanie atrybutów modelu
     * @param request zapytanie HTTP
     * @return widok ofert sklepu
     */
    @GetMapping("/laptops-and-computers")
    public String getLaptopsAndComputers(Model model, HttpServletRequest request){
        int page = 0;
        int size = 6;

        if (request.getParameter("page") != null && !request.getParameter("page").isEmpty()) {
            page = Integer.parseInt(request.getParameter("page")) - 1;
        }

        if (request.getParameter("size") != null && !request.getParameter("size").isEmpty()) {
            size = Integer.parseInt(request.getParameter("size"));
        }

        model.addAttribute("productList", productService.getByCategory("Laptopy i komputery",PageRequest.of(page, size)));
        return "views/category";
    }

    /**
     * Metoda dzieląca Produkty na
     * kategorie "Podzespoły komputerowe"
     * @param model przechowywanie atrybutów modelu
     * @param request zapytanie HTTP
     * @return widok ofert sklepu
     */
    @GetMapping("/computer-components")
    public String getComputerComponents(Model model, HttpServletRequest request){
        int page = 0;
        int size = 6;

        if (request.getParameter("page") != null && !request.getParameter("page").isEmpty()) {
            page = Integer.parseInt(request.getParameter("page")) - 1;
        }

        if (request.getParameter("size") != null && !request.getParameter("size").isEmpty()) {
            size = Integer.parseInt(request.getParameter("size"));
        }

        model.addAttribute("productList", productService.getByCategory("Podzespoły komputerowe",PageRequest.of(page, size)));
        return "views/category";
    }

    /**
     * Metoda dzieląca Produkty na
     * kategorie "Urządzenia peryferyjne "
     * @param model przechowywanie atrybutów modelu
     * @param request zapytanie HTTP
     * @return widok ofert sklepu
     */
    @GetMapping("/peripheral-devices")
    public String getPeripheralDevices(Model model, HttpServletRequest request){
        int page = 0;
        int size = 6;

        if (request.getParameter("page") != null && !request.getParameter("page").isEmpty()) {
            page = Integer.parseInt(request.getParameter("page")) - 1;
        }

        if (request.getParameter("size") != null && !request.getParameter("size").isEmpty()) {
            size = Integer.parseInt(request.getParameter("size"));
        }

        model.addAttribute("productList", productService.getByCategory("Urządzenia peryferyjne",PageRequest.of(page, size)));
        return "views/category";
    }

    /**
     ** Metoda dzieląca Produkty na
     * kategorie "Oprogramowanie "
     * @param model przechowywanie atrybutów modelu
     * @param request zapytanie HTTP
     * @return widok ofert sklepu
     */
    @GetMapping("/software")
    public String getSoftware(Model model, HttpServletRequest request){
        int page = 0;
        int size = 6;

        if (request.getParameter("page") != null && !request.getParameter("page").isEmpty()) {
            page = Integer.parseInt(request.getParameter("page")) - 1;
        }

        if (request.getParameter("size") != null && !request.getParameter("size").isEmpty()) {
            size = Integer.parseInt(request.getParameter("size"));
        }

        model.addAttribute("productList", productService.getByCategory("Oprogramowanie",PageRequest.of(page, size)));
        return "views/category";
    }

    /**
     ** Metoda dzieląca Produkty na
     * kategorie "Akcesoria "
     * @param model przechowywanie atrybutów modelu
     * @param request zapytanie HTTP
     * @return widok ofert sklepu
     */
    @GetMapping("/accessories")
    public String getAccessories(Model model, HttpServletRequest request){
        int page = 0;
        int size = 6;

        if (request.getParameter("page") != null && !request.getParameter("page").isEmpty()) {
            page = Integer.parseInt(request.getParameter("page")) - 1;
        }

        if (request.getParameter("size") != null && !request.getParameter("size").isEmpty()) {
            size = Integer.parseInt(request.getParameter("size"));
        }

        model.addAttribute("productList", productService.getByCategory("Akcesoria",PageRequest.of(page, size)));
        return "views/category";
    }

    /**
     * Metoda wyszukuje ze sklepu wybranej frazy
     * @param name nazwa wyszukiwanej frazy
     * @param model przechowywanie atrybutów modelu
     * @return widok ofert sklepu
     */
    @GetMapping("/search")
    public String showHomePage(@RequestParam(name = "name",defaultValue = "")String name, Model model){
        List<Product> productList = productService.getByNameOrCategory(name);
        model.addAttribute("productList", productList);
        return "views/search";
    }
}
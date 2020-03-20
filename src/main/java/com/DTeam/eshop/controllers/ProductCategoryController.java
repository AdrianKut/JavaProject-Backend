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

@Controller
public class ProductCategoryController {

    @Autowired
    private ProductService productService;

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

    @GetMapping("/search")
    public String showHomePage(@RequestParam(name = "name",defaultValue = "")String name, Model model){
        List<Product> productList = productService.getByNameOrCategory(name);
        model.addAttribute("productList", productList);
        return "views/search";
    }
}
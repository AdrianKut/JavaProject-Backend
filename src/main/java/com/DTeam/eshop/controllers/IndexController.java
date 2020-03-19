package com.DTeam.eshop.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.DTeam.eshop.entities.Product;
import com.DTeam.eshop.repositories.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping(value = "/")
    public String showHomePage(HttpServletRequest request, Model model){
        int page = 0; //default page number is 0
        int size = 10; //default page size is 10

        if (request.getParameter("page") != null && !request.getParameter("page").isEmpty()) {
            page = Integer.parseInt(request.getParameter("page")) - 1;
        }

        if (request.getParameter("size") != null && !request.getParameter("size").isEmpty()) {
            size = Integer.parseInt(request.getParameter("size"));
        }

        model.addAttribute("productList", productRepository.findAll(PageRequest.of(page, size)));
        return "/index";
    }


    @GetMapping("/search")
    public String showHomePage(@RequestParam(name = "name",defaultValue = "")String name, Model model){
        List<Product> productList = productRepository.findByNameContaining(name);
        model.addAttribute("productList", productList);
        return "views/search";
    }
}
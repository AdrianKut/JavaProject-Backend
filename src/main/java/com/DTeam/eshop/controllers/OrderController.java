package com.DTeam.eshop.controllers;

import java.util.ArrayList;
import java.util.List;

import com.DTeam.eshop.entities.Order;
import com.DTeam.eshop.entities.Product;
import com.DTeam.eshop.services.OrderService;
import com.DTeam.eshop.services.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductService productService;

    @GetMapping("/order/list")
    public String getAll(Model model){
        List<Order> orderList = orderService.listAll();
        model.addAttribute("orderList",orderList);
        return "views/order/list";
    }

    @GetMapping("/order/add")
    public String save(Model model) {
        List<Product> productList = productService.listAll();
        Order order = new Order();
        model.addAttribute("order", order);
        model.addAttribute("productList", productList);
        return "views/order/add";
    }

    @PostMapping("/order/add")
    public String save(Order order,
    @RequestParam(name="product",required = false)ArrayList<Product> product){

        orderService.save(order,product);
        return "redirect:/order/list";
    }

    @GetMapping("/order/edit/{id}")
    public String edit(Model model,@PathVariable(name = "id") long id){
        List<Product> productList = productService.listAll();
        Order order = orderService.get(id);
        model.addAttribute("order", order);
        model.addAttribute("productList", productList);
        return "views/order/edit";
    }

    @PostMapping("/order/edit/{id}")
    public String edit(@PathVariable(name = "id") long id,
    Order order,
    @RequestParam(name="product",required = false)ArrayList<Product> product){

        order.setOrderId(id);
        orderService.save(order,product);
        return "redirect:/order/list";
    }

    @GetMapping("/order/delete/{id}")
    public String delete(@PathVariable(name = "id") long id){
        orderService.delete(id);
        return "redirect:/order/list";
    }
}
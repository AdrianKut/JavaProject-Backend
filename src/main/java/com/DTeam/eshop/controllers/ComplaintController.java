package com.DTeam.eshop.controllers;

import java.util.List;

import com.DTeam.eshop.entities.Complaint;
import com.DTeam.eshop.entities.Order;
import com.DTeam.eshop.entities.Product;
import com.DTeam.eshop.services.ComplaintService;
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
public class ComplaintController {

    @Autowired
    private ComplaintService complaintService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductService productService;

    @GetMapping("/complaint/list")
    public String getAll(Model model){
        List<Complaint> complaintList = complaintService.listAll();
        model.addAttribute("complaintList",complaintList);
        return "views/complaint/list";
    }

    @GetMapping("/complaint/add")
    public String save(Model model) {
        List<Order> orderList = orderService.listAll();
        List<Product> productList = productService.listAll();
        Complaint complaint = new Complaint();
        model.addAttribute("complaint", complaint);
        model.addAttribute("orderList", orderList);
        model.addAttribute("productList", productList);
        return "views/complaint/add";
    }

    @PostMapping("/complaint/add")
    public String save(Complaint complaint,
    @RequestParam(name="order")Order order,
    @RequestParam(name="product")Product product){
        complaint.setOrder(order);
        complaint.setProduct(product);
        complaintService.save(complaint);
        return "redirect:/complaint/list";
    }

    @GetMapping("/complaint/edit/{id}")
    public String edit(Model model,@PathVariable(name = "id") long id){
        List<Order> orderList = orderService.listAll();
        List<Product> productList = productService.listAll();
        Complaint complaint = complaintService.get(id);
        model.addAttribute("complaint", complaint);
        model.addAttribute("orderList", orderList);
        model.addAttribute("productList", productList);
        return "views/complaint/edit";
    }

    @PostMapping("/complaint/edit/{id}")
    public String edit(@PathVariable(name = "id") long id,
    Complaint complaint,
    @RequestParam(name="order")Order order,
    @RequestParam(name="product")Product product){
        complaint.setComplaintId(id);
        complaint.setOrder(order);
        complaint.setProduct(product);
        complaintService.save(complaint);
        return "redirect:/complaint/list";
    }

    @GetMapping("/complaint/delete/{id}")
    public String delete(@PathVariable(name = "id") long id){
        complaintService.delete(id);
        return "redirect:/complaint/list";
    }
}
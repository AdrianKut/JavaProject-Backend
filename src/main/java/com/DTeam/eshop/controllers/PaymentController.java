package com.DTeam.eshop.controllers;

import java.util.List;

import com.DTeam.eshop.entities.Order;
import com.DTeam.eshop.entities.Payment;
import com.DTeam.eshop.services.OrderService;
import com.DTeam.eshop.services.PaymentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private OrderService orderService;

    @GetMapping("/payment/list")
    public String getAll(Model model){
        List<Payment> paymentList = paymentService.listAll();
        model.addAttribute("paymentList",paymentList);
        return "views/payment/list";
    }

    @GetMapping("/payment/add")
    public String save(Model model) {
        List<Order> orderList = orderService.listAll();
        Payment payment = new Payment();
        model.addAttribute("payment", payment);
        model.addAttribute("orderList", orderList);
        return "views/payment/add";
    }

    @PostMapping("/payment/add")
    public String save(Payment payment,
        @RequestParam(name="order")Order order){
        payment.setOrder(order);
        paymentService.save(payment);
        return "redirect:/payment/list";
    }

    @GetMapping("/payment/edit/{id}")
    public String edit(Model model,@PathVariable(name = "id") long id){
        List<Order> orderList = orderService.listAll();
        Payment payment = paymentService.get(id);
        model.addAttribute("payment", payment);
        model.addAttribute("orderList", orderList);
        return "views/payment/edit";
    }

    @PostMapping("/payment/edit/{id}")
    public String edit(@PathVariable(name = "id") long id, Payment payment,
        @RequestParam(name="order")Order order){
        payment.setPaymentId(id);
        payment.setOrder(order);
        paymentService.save(payment);
        return "redirect:/payment/list";
    }

    @GetMapping("/payment/delete/{id}")
    public String delete(@PathVariable(name = "id") long id){
        paymentService.delete(id);
        return "redirect:/payment/list";
    }


}
package com.DTeam.eshop.controllers;

import java.util.List;


import com.DTeam.eshop.entities.Payment;
import com.DTeam.eshop.services.PaymentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @RequestMapping(value = "/payment/list", method = RequestMethod.GET)
    public String getAll(Model model){
        List<Payment> paymentList = paymentService.listAll();
        model.addAttribute("paymentList",paymentList);
        return "/views/payment/list";
    }

    @RequestMapping(value = "/payment/add", method = RequestMethod.GET)
    public String save(Model model) {
        Payment payment = new Payment();
        model.addAttribute("payment", payment);
        return "/views/payment/add";
    }

    @RequestMapping(value = "/payment/add", method = RequestMethod.POST)
    public String save(Payment payment){
         
        paymentService.save(payment);
        return "redirect:/payment/list";
    }

    @RequestMapping(value = "/payment/edit/{id}", method = RequestMethod.GET)
    public String edit(Model model,@PathVariable(name = "id") long id){
        
        Payment payment = paymentService.get(id);
        model.addAttribute("payment", payment);
        return "/views/payment/edit";
    }
    
    @RequestMapping(value = "/payment/edit/{id}", method = RequestMethod.POST)
    public String edit(@PathVariable(name = "id") long id, 
    Payment payment){ 
       
      payment.setPaymentId(id);
      paymentService.save(payment);
        return "redirect:/payment/list";
    }

    @RequestMapping(value = "/payment/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable(name = "id") long id){
        paymentService.delete(id);
        return "redirect:/payment/list";
    }


}
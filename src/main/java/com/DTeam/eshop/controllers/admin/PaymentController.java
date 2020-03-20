package com.DTeam.eshop.controllers.admin;

import java.util.List;

import com.DTeam.eshop.entities.Payment;
import com.DTeam.eshop.services.PaymentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @GetMapping("/admin/payment/list")
    public String getAll(Model model){
        List<Payment> paymentList = paymentService.listAll();
        model.addAttribute("paymentList",paymentList);
        return "views/admin/payment/list";
    }

    @GetMapping("/admin/payment/add")
    public String save(Model model) {
        Payment payment = new Payment();
        model.addAttribute("payment", payment);
        return "views/admin/payment/add";
    }

    @PostMapping("/admin/payment/add")
    public String save(Payment payment){
        paymentService.save(payment);
        return "redirect:/admin/payment/list";
    }

    @GetMapping("/admin/payment/edit/{id}")
    public String edit(Model model,@PathVariable(name = "id") long id){
        Payment payment = paymentService.get(id);
        model.addAttribute("payment", payment);
        return "views/admin/payment/edit";
    }

    @PostMapping("/admin/payment/edit/{id}")
    public String edit(@PathVariable(name = "id") long id, Payment payment){
        payment.setPaymentId(id);
        paymentService.save(payment);
        return "redirect:/admin/payment/list";
    }

    @GetMapping("/admin/payment/delete/{id}")
    public String delete(@PathVariable(name = "id") long id){
        paymentService.delete(id);
        return "redirect:/admin/payment/list";
    }
}
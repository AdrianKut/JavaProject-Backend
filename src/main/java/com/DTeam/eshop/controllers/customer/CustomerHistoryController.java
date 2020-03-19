package com.DTeam.eshop.controllers.customer;

import java.security.Principal;
import java.util.List;

import com.DTeam.eshop.entities.Customer;
import com.DTeam.eshop.entities.Order;
import com.DTeam.eshop.services.CustomerService;
import com.DTeam.eshop.services.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CustomerHistoryController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private OrderService orderService;

    @GetMapping("/customer/order")
    public String getComplaint(Model model, Principal principal) {

        String email = principal.getName();
        if (customerService.isCustomerExist(email)) {
            Customer customer = customerService.getByEmail(email);
            List<Order> orderList = orderService.getByCustomer(customer);
            model.addAttribute("orderList", orderList);
            return "views/customer/order";

        } else{
            return "redirect:/";
        }
    }

}
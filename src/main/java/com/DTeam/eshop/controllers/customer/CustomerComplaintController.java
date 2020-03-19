package com.DTeam.eshop.controllers.customer;

import java.security.Principal;
import java.util.List;

import com.DTeam.eshop.entities.Complaint;
import com.DTeam.eshop.entities.Customer;
import com.DTeam.eshop.entities.Order;
import com.DTeam.eshop.entities.Product;
import com.DTeam.eshop.services.ComplaintService;
import com.DTeam.eshop.services.CustomerService;
import com.DTeam.eshop.services.OrderService;
import com.DTeam.eshop.services.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class CustomerComplaintController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductService productService;

    @Autowired
    private ComplaintService complaintService;

    @GetMapping("/customer/complaint")
    public String getComplaint(Model model, Principal principal) {

        String email = principal.getName();
        if (customerService.isCustomerExist(email)) {
            Customer customer = customerService.getByEmail(email);
            List<Order> orderList = orderService.getByCustomer(customer);
            model.addAttribute("orderList", orderList);
            return "views/customer/complaint";
            
        } else{
            return "/index";
        } 
    }

    @GetMapping("/customer/addcomplaint/{id}")
    public String edit(@PathVariable(name = "id")Long id, Model model){
        List<Order> orderList = orderService.listAll();
        List<Product> productList = productService.listAll();
        Complaint complaint = complaintService.get(id);
        model.addAttribute("complaint", complaint);
        model.addAttribute("productList", productList);
        model.addAttribute("orderList", orderList);
        return "views/customer/addcomplaint";
    }

}
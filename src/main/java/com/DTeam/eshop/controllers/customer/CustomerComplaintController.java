package com.DTeam.eshop.controllers.customer;

import java.security.Principal;
import java.time.LocalDateTime;
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
import org.springframework.web.bind.annotation.PostMapping;

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
            List<Complaint> complaintList = complaintService.getByCustomer(customer);
            model.addAttribute("complaintList", complaintList);
            return "views/customer/complaint";
        } else {
            return "redirect:/";
        }
    }

    @GetMapping("/customer/complaint/add/{orderId}/{productId}")
    public String add(@PathVariable(name = "orderId")Long orderId,
        @PathVariable(name = "productId")Long productId, Model model){
        Complaint complaint = new Complaint();
        Order order = orderService.get(orderId);
        Product product = productService.get(productId);
        model.addAttribute("order", order);
        model.addAttribute("product", product);
        model.addAttribute("complaint", complaint);
        return "views/customer/addcomplaint";
    }

    @PostMapping("/customer/complaint/add/{orderId}/{productId}")
    public String add(@PathVariable(name = "orderId")Long orderId,
        @PathVariable(name = "productId")Long productId, Complaint complaint){
        LocalDateTime dateTime = LocalDateTime.now();
        complaint.setNotificationDate(dateTime.toString());
        complaint.setOrder(orderService.get(orderId));
        complaint.setProduct(productService.get(productId));
        complaint.setComplaintStatus("Przyjęta");
        complaintService.save(complaint);
        return "redirect:/customer/complaint";
    }
}
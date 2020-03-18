package com.DTeam.eshop.controllers;

import java.util.List;

import com.DTeam.eshop.entities.Address;
import com.DTeam.eshop.entities.Customer;
import com.DTeam.eshop.entities.User;
import com.DTeam.eshop.services.AddressService;
import com.DTeam.eshop.services.CustomerService;
import com.DTeam.eshop.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private UserService userService;

    @Autowired
    private AddressService addressService;

    @GetMapping("/customer/list")
    public String getAll(Model model){
        List<Customer> customerList = customerService.listAll();
        model.addAttribute("customerList", customerList);
        return "views/customer/list";
    }

    @GetMapping("/customer/add")
    public String save(Model model){
        List<User> userList = userService.listAll();
        List<Address> addressList = addressService.listAll();
        Customer customer = new Customer();
        model.addAttribute("customer", customer);
        model.addAttribute("userList", userList);
        model.addAttribute("addressList", addressList);
        return "views/customer/add";
    }

    @PostMapping("/customer/add")
    public String save(Customer customer,
        @RequestParam(name = "users")User user,
        @RequestParam(name = "addresses")Address address){
        customer.setUser(user);
        customer.setAddress(address);
        customerService.save(customer);
        return "redirect:/customer/list";
    }

    @GetMapping("/customer/edit/{id}")
    public String edit(@PathVariable(name = "id")Long id, Model model){
        List<User> userList = userService.listAll();
        List<Address> addressList = addressService.listAll();
        Customer customer = customerService.get(id);
        model.addAttribute("customer", customer);
        model.addAttribute("userList", userList);
        model.addAttribute("addressList", addressList);
        return "views/customer/edit";
    }

    @PostMapping("/customer/edit/{id}")
    public String edit(@PathVariable(name = "id")Long id, Customer customer,
    @RequestParam(name = "users")User user,
    @RequestParam(name = "addresses")Address address){
        customer.setCustomerId(id);
        customer.setUser(user);
        customer.setAddress(address);
        customerService.save(customer);
        return "redirect:/customer/list";
    }

    @GetMapping("/customer/delete/{id}")
    public String delete(@PathVariable(name = "id")Long id){
        customerService.delete(id);
        return "redirect:/customer/list";
    }
}
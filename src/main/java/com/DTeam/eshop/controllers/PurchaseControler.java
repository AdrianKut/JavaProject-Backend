package com.DTeam.eshop.controllers;

import java.time.LocalDateTime;
import java.util.List;

import com.DTeam.eshop.entities.Address;
import com.DTeam.eshop.entities.Customer;
import com.DTeam.eshop.entities.Order;
import com.DTeam.eshop.entities.Payment;
import com.DTeam.eshop.entities.Product;
import com.DTeam.eshop.services.AddressService;
import com.DTeam.eshop.services.CustomerService;
import com.DTeam.eshop.services.OrderService;
import com.DTeam.eshop.services.PaymentService;
import com.DTeam.eshop.services.ProductService;
import com.DTeam.eshop.utilities.ShoppingCart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PurchaseControler {

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private AddressService addressService;

    @Autowired
    private ShoppingCart shoppingCart;

    @GetMapping("/shopping-cart/buy/no-register")
    public String ShoppingCartNoRegister(Model model){
        Address address = new Address();
        Customer customer = new Customer();
        Payment payment = new Payment();
        Double amount = shoppingCart.getAmount();
        model.addAttribute("address", address);
        model.addAttribute("customer", customer);
        model.addAttribute("payment", payment);
        model.addAttribute("amount", amount);
        return "views/noRegisterBuy";
    }

    @PostMapping("/shopping-cart/buy/no-register")
    public String ShoppingCartNoRegister(Customer customer, Address address,
     Payment payment, @RequestParam("email")String email){
        addressService.save(address);
        customer.setAddress(address);
        customerService.save(customer);
        Order order = new Order();
        order.setCustomer(customer);
        order.setOrderStatus("Przyjęte");
        LocalDateTime dateTime = LocalDateTime.now();
        order.setPurchaseDate(dateTime.toString());
        List<Product> productList = shoppingCart.get();
        orderService.save(order, productList);
        //payment
        return "/";
    }
}
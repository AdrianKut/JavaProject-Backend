package com.DTeam.eshop.controllers;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
import com.DTeam.eshop.utilities.EmailSender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

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

    @Autowired
    private TemplateEngine templateEngine;

    @Autowired
    private EmailSender emailSender;

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
        return "views/noRegisterBuyCart";
    }

    @PostMapping("/shopping-cart/buy/no-register")
    public String ShoppingCartNoRegister(Customer customer, Address address,
     Payment payment, @RequestParam("email")String email){
        addressService.save(address);
        customer.setAddress(address);
        customerService.save(customer);
        LocalDateTime dateTime = LocalDateTime.now();
        payment.setPaymentDate(dateTime.toString());
        payment.setAmount(shoppingCart.getAmount());
        paymentService.save(payment);
        Order order = new Order();
        order.setCustomer(customer);
        order.setOrderStatus("Przyjęte");
        order.setPurchaseDate(dateTime.toString());
        order.setPayment(payment);
        List<Product> productList = shoppingCart.get();
        for (Product product : productList) {
            product.setQuantity(product.getQuantity()-1);
            productService.save(product);
        }
        orderService.save(order, productList);
        shoppingCart.clear();

        Context context = new Context();
        context.setVariable("header", "Dziękujemy za zakup!");
        context.setVariable("title", "Witaj, " + email);
        context.setVariable("description", "Dziękujęmy, że zakupiłeś produkty w naszym sklepie. Postaramy się, aby jak " +
        "najszybciej do Ciebie dotarły. Pozdrawiamy :)");
        String body = templateEngine.process("template", context);
        emailSender.sendEmail(email, "Zakup produktów", body);

        return "redirect:/shopping-cart";
    }

    @GetMapping("/buy/no-register/{id}")
    public String PurchaseNoRegister(Model model, @PathVariable(name = "id")Long id){
        Address address = new Address();
        Customer customer = new Customer();
        Payment payment = new Payment();
        Product product = productService.get(id);
        model.addAttribute("address", address);
        model.addAttribute("customer", customer);
        model.addAttribute("payment", payment);
        model.addAttribute("amount", product.getPrice());
        return "views/noRegisterBuy";
    }

    @PostMapping("/buy/no-register/{id}")
    public String PurchaseNoRegister(Customer customer, Address address,
     Payment payment, @RequestParam("email")String email, @PathVariable(name = "id")Long id){
        Product product = productService.get(id);
        product.setQuantity(product.getQuantity()-1);
        productService.save(product);
        addressService.save(address);
        customer.setAddress(address);
        customerService.save(customer);
        LocalDateTime dateTime = LocalDateTime.now();
        payment.setPaymentDate(dateTime.toString());
        payment.setAmount(product.getPrice());
        paymentService.save(payment);
        Order order = new Order();
        order.setCustomer(customer);
        order.setOrderStatus("Przyjęte");
        order.setPurchaseDate(dateTime.toString());
        order.setPayment(payment);
        List<Product> productList = new ArrayList<Product>();
        productList.add(product);
        orderService.save(order, productList);

        Context context = new Context();
        context.setVariable("header", "Dziękujemy za zakup!");
        context.setVariable("title", "Witaj, " + email);
        context.setVariable("description", "Dziękujęmy, że zakupiłeś produkty w naszym sklepie. Postaramy się, aby jak " +
        "najszybciej do Ciebie dotarły. Pozdrawiamy :)");
        String body = templateEngine.process("template", context);
        emailSender.sendEmail(email, "Zakup produktów", body);

        return "redirect:/";
    }
}
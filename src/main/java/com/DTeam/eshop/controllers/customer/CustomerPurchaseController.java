package com.DTeam.eshop.controllers.customer;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.DTeam.eshop.entities.Customer;
import com.DTeam.eshop.entities.Order;
import com.DTeam.eshop.entities.Payment;
import com.DTeam.eshop.entities.Product;
import com.DTeam.eshop.services.AddressService;
import com.DTeam.eshop.services.CustomerService;
import com.DTeam.eshop.services.OrderService;
import com.DTeam.eshop.services.PaymentService;
import com.DTeam.eshop.services.ProductService;
import com.DTeam.eshop.utilities.EmailSender;
import com.DTeam.eshop.utilities.ShoppingCart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Controller
public class CustomerPurchaseController {

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ShoppingCart shoppingCart;

    @Autowired
    private TemplateEngine templateEngine;

    @Autowired
    private EmailSender emailSender;

    @GetMapping("/customer/shopping-cart/buy")
    public String shoppingCart(Model model, Principal principal){
        String email = principal.getName();
        if (customerService.isCustomerExist(email)) {
            Payment payment = new Payment();
            Double amount = shoppingCart.getAmount();
            model.addAttribute("payment", payment);
            model.addAttribute("amount", amount);
            return "views/customer/buyShoppingCart";
        } else {
            return "redirect:/shopping-cart";
        }
    }

    @PostMapping("/customer/shopping-cart/buy")
    public String shoppingCart(Payment payment, Principal principal){
        String email = principal.getName();
        LocalDateTime dateTime = LocalDateTime.now();
        payment.setPaymentDate(dateTime.toString());
        payment.setAmount(shoppingCart.getAmount());
        paymentService.save(payment);
        Order order = new Order();
        Customer customer = customerService.getByEmail(email);
        order.setCustomer(customer);
        order.setPurchaseDate(dateTime.toString());
        order.setOrderStatus("Przyjęte");
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

    @GetMapping("/customer/buy/{id}")
    public String buyProduct(Model model, Principal principal,
     @PathVariable(name = "id")Long id){
        String email = principal.getName();
        if (customerService.isCustomerExist(email)) {
            Payment payment = new Payment();
            Product product = productService.get(id);
            model.addAttribute("payment", payment);
            model.addAttribute("amount", product.getPrice());
            return "views/customer/buy";
        } else {
            return "redirect:/";
        }
    }

    @PostMapping("/customer/buy/{id}")
    public String buyProduct(Payment payment, Principal principal,
    @PathVariable(name = "id")Long id){
        Product product = productService.get(id);
        product.setQuantity(product.getQuantity()-1);
        productService.save(product);
        String email = principal.getName();
        LocalDateTime dateTime = LocalDateTime.now();
        payment.setPaymentDate(dateTime.toString());
        payment.setAmount(product.getPrice());
        paymentService.save(payment);
        Order order = new Order();
        Customer customer = customerService.getByEmail(email);
        order.setCustomer(customer);
        order.setPurchaseDate(dateTime.toString());
        order.setOrderStatus("Przyjęte");
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

        return "redirect:/customer/order";
    }
}
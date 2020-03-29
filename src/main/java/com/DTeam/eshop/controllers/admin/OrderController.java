package com.DTeam.eshop.controllers.admin;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import com.DTeam.eshop.entities.Customer;
import com.DTeam.eshop.entities.Order;
import com.DTeam.eshop.entities.Payment;
import com.DTeam.eshop.entities.Product;
import com.DTeam.eshop.services.CustomerService;
import com.DTeam.eshop.services.OrderService;
import com.DTeam.eshop.services.PaymentService;
import com.DTeam.eshop.services.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Klasa obsługująca Zamówiena
 * @author 
 */
@Controller
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductService productService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private PaymentService paymentService;

    /**
     * Metoda wyświetlająca liste Zamówień
     * @param model przechowywanie atrybutów modelu
     * @return widok list Zamówień
     */
    @GetMapping("/admin/order/list")
    public String getAll(Model model){
        List<Order> orderList = orderService.listAll();
        model.addAttribute("orderList",orderList);
        return "views/admin/order/list";
    }

    /**
     * Metoda dodająca Zamówienie
     * @param model przechowywanie atrybutów modelu
     * @return widok formularza Zamówienia
     */
    @GetMapping("/admin/order/add")
    public String save(Model model) {
        List<Product> productList = productService.listAll();
        List<Customer> customerList = customerService.listAll();
        List<Payment> paymentList = paymentService.listAll();
        Order order = new Order();
        model.addAttribute("order", order);
        model.addAttribute("productList", productList);
        model.addAttribute("customerList", customerList);
        model.addAttribute("paymentList", paymentList);
        return "views/admin/order/add";
    }

    /**
     * Metoda dodająca Zamówienie
     * @param order przechowuje Dane Zamówienia
     * @param bindingResult walidacja błędów
     * @param product przechowuje Dane Produktów
     * @param customer Przechowuje Dane Klienta
     * @param payment Przechowuje Dane Sprzedaży
     * @return widok list Zamówień
     */
    @PostMapping("/admin/order/add")
    public String save(@Valid Order order, BindingResult bindingResult,
    @RequestParam(name="product",required = false)ArrayList<Product> product,
    @RequestParam(name="customer")Customer customer,
    @RequestParam(name="payment")Payment payment){
        if(bindingResult.hasErrors()){
            return "views/admin/order/add";
        }
        order.setPayment(payment);
        order.setCustomer(customer);
        orderService.save(order,product);
        return "redirect:/admin/order/list";
    }

    /**
     * Metoda edycji Zamówienia
     * @param model przechowywanie atrybutów modelu
     * @param id przechowuje Id Zamówienia
     * @return widok edycji Zamówienia
     */
    @GetMapping("/admin/order/edit/{id}")
    public String edit(Model model,@PathVariable(name = "id") long id){
        List<Product> productList = productService.listAll();
        List<Customer> customerList = customerService.listAll();
        List<Payment> paymentList = paymentService.listAll();
        Order order = orderService.get(id);
        model.addAttribute("order", order);
        model.addAttribute("productList", productList);
        model.addAttribute("customerList", customerList);
        model.addAttribute("paymentList", paymentList);
        return "views/admin/order/edit";
    }

    /**
     * Metoda edycji Zamówienia
     * @param id przechowuje Id Zamówienia
     * @param order przechowuje Dane Zamówienia
     * @param bindingResult walidacja błędów
     * @param product przechowuje Dane Produktów
     * @param customer przechowuje Dane Klienta
     * @param payment przechowuje Dane Sprzedaży
     * @return widok list Zamówień
     */
    @PostMapping("/admin/order/edit/{id}")
    public String edit(@PathVariable(name = "id") long id, @Valid Order order, BindingResult bindingResult,
    @RequestParam(name="product",required = false)ArrayList<Product> product,
    @RequestParam(name="customer")Customer customer,
    @RequestParam(name="payment")Payment payment){
        if(bindingResult.hasErrors()){
            return "views/admin/order/edit";
        }
        order.setOrderId(id);
        order.setPayment(payment);
        order.setCustomer(customer);
        orderService.save(order,product);
        return "redirect:/admin/order/list";
    }

    /**
     * Metoda usuwająca Zamówienie 
     * @param id przechowuje Id Zamówienia
     * @return widok list Zamówień
     */
    @GetMapping("/admin/order/delete/{id}")
    public String delete(@PathVariable(name = "id") long id){
        orderService.delete(id);
        return "redirect:/admin/order/list";
    }
}
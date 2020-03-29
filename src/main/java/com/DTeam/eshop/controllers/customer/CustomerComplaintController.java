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
import com.DTeam.eshop.utilities.EmailSender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

/**
 * Klasa obsugująca Skargi-klientów
 * @author 
 */
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

    @Autowired
    private TemplateEngine templateEngine;

    @Autowired
    private EmailSender emailSender;

    /**
     *Metoda Wyświetla skargi Danego Klienta
     * @param model przechowywanie atrybutów modelu
     * @param principal pchechowuje emile Klienta
     * @return widok listy Skarg Danego Klienta
     */
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

    /**
     * Metoda dodająca skarde do danego Klienta
     * @param orderId przechowuje ID Zamówienia
     * @param productId przechowuje Id Produktu
     * @param model przechowywanie atrybutów modelu
     * @return widok forlmularza Skargi
     */
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

    /**
     * Metoda dodająca skarde do danego Klienta
     * @param orderId przechowuje Id Zamówienia
     * @param productId przechowuje Id Produktu
     * @param complaint przechowuje Dane Skargi
     * @param principal przechowuje email Klienta
     * @return  widok listy Skarg Danego Klienta
     */
    @PostMapping("/customer/complaint/add/{orderId}/{productId}")
    public String add(@PathVariable(name = "orderId")Long orderId,
        @PathVariable(name = "productId")Long productId, Complaint complaint, Principal principal){
        LocalDateTime dateTime = LocalDateTime.now();
        complaint.setNotificationDate(dateTime.toString());
        complaint.setOrder(orderService.get(orderId));
        complaint.setProduct(productService.get(productId));
        complaint.setComplaintStatus("Przyjęta");
        complaintService.save(complaint);

        String email = principal.getName();
        Product product = productService.get(productId);
        Context context = new Context();
        context.setVariable("header", "Dziękujemy za zgłoszenie!");
        context.setVariable("title", "Witaj, " + email);
        context.setVariable("description", "Zarejestowaliśmy właśnie zgłoszenie reklamacji produktu \"" + product.getName() +
        "\". Postaramy się je jak najszybciej rozpatrzyć. Pozdrawiamy :)");
        String body = templateEngine.process("template", context);
        emailSender.sendEmail(email, "Zgłoszenie reklamacji", body);

        return "redirect:/customer/complaint";
    }
}
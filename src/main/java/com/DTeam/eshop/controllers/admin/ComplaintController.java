package com.DTeam.eshop.controllers.admin;

import java.util.List;

import javax.validation.Valid;

import com.DTeam.eshop.entities.Complaint;
import com.DTeam.eshop.entities.Order;
import com.DTeam.eshop.entities.Product;
import com.DTeam.eshop.services.ComplaintService;
import com.DTeam.eshop.services.OrderService;
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
 * Klasa obsługująca Reklamacje
 * @author 
 */
@Controller
public class ComplaintController {

    @Autowired
    private ComplaintService complaintService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductService productService;

    /**
     * Metoda wyświetlająca liste Reklamacji
     * @param model przechowywanie atrybutów modelu
     * @return widok listy Reklamacji
     */
    @GetMapping("/admin/complaint/list")
    public String getAll(Model model){
        List<Complaint> complaintList = complaintService.listAll();
        model.addAttribute("complaintList",complaintList);
        return "views/admin/complaint/list";
    }

    /**
     * Metoda dodawania Reklamacji
     * @param model przechowywanie atrybutów modelu
     * @return widok formularza Reklamacji
     */
    @GetMapping("/admin/complaint/add")
    public String save(Model model) {
        List<Order> orderList = orderService.listAll();
        List<Product> productList = productService.listAll();
        Complaint complaint = new Complaint();
        model.addAttribute("complaint", complaint);
        model.addAttribute("orderList", orderList);
        model.addAttribute("productList", productList);
        return "views/admin/complaint/add";
    }

    /**
     * Metoda dodawania Reklamacji
     * @param complaint przechowuje dane Reklamacji
     * @param bindingResult walidacja błędów
     * @param order przechowuje dane zamównienia
     * @param product przechowuje danie produktu
     * @return Widok listy reklamacji
     */
    @PostMapping("/admin/complaint/add")
    public String save(@Valid Complaint complaint, BindingResult bindingResult,
    @RequestParam(name="order")Order order,
    @RequestParam(name="product")Product product){
        if(bindingResult.hasErrors()){
            return "views/admin/complaint/add";
        }
        complaint.setOrder(order);
        complaint.setProduct(product);
        complaintService.save(complaint);
        return "redirect:/admin/complaint/list";
    }

    /**
     * Edycja Reklamacji
     * @param model przechowywanie atrybutów modelu
     * @param id przechowuje id Reklamacji
     * @return widok formularza edycji Reklamacji
     */
    @GetMapping("/admin/complaint/edit/{id}")
    public String edit(Model model,@PathVariable(name = "id") long id){
        List<Order> orderList = orderService.listAll();
        List<Product> productList = productService.listAll();
        Complaint complaint = complaintService.get(id);
        model.addAttribute("complaint", complaint);
        model.addAttribute("orderList", orderList);
        model.addAttribute("productList", productList);
        return "views/admin/complaint/edit";
    }

    /**
     * Edycja Reklamacji
     * @param id przechowuje id Reklamacji
     * @param complaint przechowuje Dane Reklamacji
     * @param bindingResult walidacja błędów
     * @param order przechowuje dane zamówienia
     * @param product przechowuje dane produktu 
     * @return widok listy Reklamacji
     */
    @PostMapping("/admin/complaint/edit/{id}")
    public String edit(@PathVariable(name = "id") long id,
    @Valid Complaint complaint, BindingResult bindingResult,
    @RequestParam(name="order")Order order,
    @RequestParam(name="product")Product product){
        if(bindingResult.hasErrors()){
            return "views/admin/complaint/edit";
        }
        complaint.setComplaintId(id);
        complaint.setOrder(order);
        complaint.setProduct(product);
        complaintService.save(complaint);
        return "redirect:/admin/complaint/list";
    }

    /**
     * Metoda usuwania Reklamacji
     * @param id przechowuje Id Reklamacji
     * @return widok listy Reklamacjii
     */
    @GetMapping("/admin/complaint/delete/{id}")
    public String delete(@PathVariable(name = "id") long id){
        complaintService.delete(id);
        return "redirect:/admin/complaint/list";
    }
}
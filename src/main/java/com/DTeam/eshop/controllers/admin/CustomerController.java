package com.DTeam.eshop.controllers.admin;

import java.util.List;

import javax.validation.Valid;

import com.DTeam.eshop.entities.Address;
import com.DTeam.eshop.entities.Customer;
import com.DTeam.eshop.entities.User;
import com.DTeam.eshop.services.AddressService;
import com.DTeam.eshop.services.CustomerService;
import com.DTeam.eshop.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Klasa obsługująca Klientów
 * @author 
 */
@Controller
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private UserService userService;

    @Autowired
    private AddressService addressService;

    /**
     * Metoda wyświetlania listy Klientów
     * @param model przechowywanie atrybutów modelu
     * @return widok listy Klientów
     */
    @GetMapping("/admin/customer/list")
    public String getAll(Model model){
        List<Customer> customerList = customerService.listAll();
        model.addAttribute("customerList", customerList);
        return "views/admin/customer/list";
    }

    /**
     * Metoda dodawania Klienta
     * @param model przechowywanie atrybutów modelu
     * @return widok formularza Klienta
     */
    @GetMapping("/admin/customer/add")
    public String save(Model model){
        List<User> userList = userService.listAll();
        List<Address> addressList = addressService.listAll();
        Customer customer = new Customer();
        model.addAttribute("customer", customer);
        model.addAttribute("userList", userList);
        model.addAttribute("addressList", addressList);
        return "views/admin/customer/add";
    }

    /**
     * Metoda dodawania Klienta
     * @param customer przechowuje Id Klienta
     * @param bindingResult walidacja błędów
     * @param user przechowuje dane Użytkownika
     * @param address przechowuje adres Klienta
     * @return widok listy Klientów
     */
    @PostMapping("/admin/customer/add")
    public String save(@Valid Customer customer, BindingResult bindingResult,
        @RequestParam(name = "users")User user,
        @RequestParam(name = "addresses")Address address){
        if(bindingResult.hasErrors()){
            return "views/admin/customer/add";
        }
        customer.setUser(user);
        customer.setAddress(address);
        customerService.save(customer);
        return "redirect:/admin/customer/list";
    }

    /**
     * Metoda edytowania Klienta
     * @param id przechowuje Id Klienta
     * @param model przechowywanie atrybutów modelu
     * @return widok formularza edycji Klienta
     */
    @GetMapping("/admin/customer/edit/{id}")
    public String edit(@PathVariable(name = "id")Long id, Model model){
        List<User> userList = userService.listAll();
        List<Address> addressList = addressService.listAll();
        Customer customer = customerService.get(id);
        model.addAttribute("customer", customer);
        model.addAttribute("userList", userList);
        model.addAttribute("addressList", addressList);
        return "views/admin/customer/edit";
    }

    /**
     * Metoda edytowania Klienta
     * @param id przechowuje Id Klienta
     * @param customer Przechowuje Dane Klienta
     * @param bindingResult walidacja Błędów
     * @param user przechowuje Dane Uzytkownika
     * @param address przechowuje adres Klienta
     * @return widok listy Klientów
     */
    @PostMapping("/admin/customer/edit/{id}")
    public String edit(@PathVariable(name = "id")Long id, @Valid Customer customer, BindingResult bindingResult,
    @RequestParam(name = "users")User user,
    @RequestParam(name = "addresses")Address address){
        if(bindingResult.hasErrors()){
            return "views/admin/customer/edit";
        }
        customer.setCustomerId(id);
        customer.setUser(user);
        customer.setAddress(address);
        customerService.save(customer);
        return "redirect:/admin/customer/list";
    }

    /**
     * Metoda usuwania Klienta
     * @param id przechowuje Id Klienta
     * @return widok list Klientów
     */
    @GetMapping("/admin/customer/delete/{id}")
    public String delete(@PathVariable(name = "id")Long id){
        customerService.delete(id);
        return "redirect:/admin/customer/list";
    }
}
package com.DTeam.eshop.controllers.admin;

import java.util.List;

import javax.validation.Valid;

import com.DTeam.eshop.entities.Address;
import com.DTeam.eshop.entities.Employee;
import com.DTeam.eshop.entities.User;
import com.DTeam.eshop.services.AddressService;
import com.DTeam.eshop.services.EmployeeService;
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
 * Klasa obsługująca Pracowników
 * @author 
 */
@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private UserService userService;

    @Autowired
    private AddressService addressService;

    /**
     * Metoda wyswietla liste Pracowników
     * @param model przechowywanie atrybutów modelu
     * @return widok listy Pracowników
     */
    @GetMapping("/admin/employee/list")
    public String getAll(Model model){
        List<Employee> employeeList = employeeService.listAll();
        model.addAttribute("employeeList", employeeList);
        return "views/admin/employee/list";
    }

    /**
     * Metoda dodaje Pracownika
     * @param model przechowywanie atrybutów modelu
     * @return widok formularza Pracownika
     */
    @GetMapping("/admin/employee/add")
    public String add(Model model){
        List<User> userList = userService.listAll();
        List<Address> addressList = addressService.listAll();
        Employee employee = new Employee();
        model.addAttribute("employee", employee);
        model.addAttribute("userList", userList);
        model.addAttribute("addressList", addressList);
        return "views/admin/employee/add";
    }

    /**
     * Metoda dodaje Pracownika
     * @param employee przechowuje dane Pracownika
     * @param bindingResult walidacja błędów
     * @param user przechowuje dane użytkownika
     * @param address przechowuje adres użytkownika
     * @return widok listy Pracowników
     */
    @PostMapping("/admin/employee/add")
    public String add(@Valid Employee employee, BindingResult bindingResult,
        @RequestParam(name = "users")User user,
        @RequestParam(name = "addresses")Address address){
        if(bindingResult.hasErrors()){
            return "views/admin/employee/add";
        }
        employee.setUser(user);
        employee.setAddress(address);
        employeeService.save(employee);
        return "redirect:/admin/employee/list";
    }

    /**
     * Metoda edycji Pracownika
     * @param id przechowuje Id Pracownika
     * @param model przechowywanie atrybutów modelu
     * @return widok edycji Pracownika
     */
    @GetMapping("/admin/employee/edit/{id}")
    public String edit(@PathVariable(name = "id")Long id, Model model){
        List<User> userList = userService.listAll();
        List<Address> addressList = addressService.listAll();
        Employee employee = employeeService.get(id);
        model.addAttribute("employee", employee);
        model.addAttribute("userList", userList);
        model.addAttribute("addressList", addressList);
        return "views/admin/employee/edit";
    }

    /**
     * Metoda edycji Pracownika
     * @param id przechowuje Id Pracownika
     * @param employee przechowuje Dane Pracownika 
     * @param bindingResult walidacja błędów
     * @param user przechowuje Dane Pracownika
     * @param address przechowuje Adres Pracownika
     * @return widok listy Pracowników
     */
    @PostMapping("/admin/employee/edit/{id}")
    public String edit(@PathVariable(name = "id")Long id, @Valid Employee employee, BindingResult bindingResult,
        @RequestParam(name = "users")User user,
        @RequestParam(name = "addresses")Address address){
        if(bindingResult.hasErrors()){
            return "views/admin/employee/edit";
        }
        employee.setUser(user);
        employee.setAddress(address);
        employee.setEmployeeId(id);
        employeeService.save(employee);
        return "redirect:/admin/employee/list";
    }

    /**
     * Metoda usuwania Pracownika
     * @param id przechowuje Id Pracownika
     * @return widok listy Pracowników
     */
    @GetMapping("/admin/employee/delete/{id}")
    public String delete(@PathVariable(name = "id")Long id){
        employeeService.delete(id);
        return "redirect:/admin/employee/list";
    }
}
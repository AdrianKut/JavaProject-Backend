package com.DTeam.eshop.controllers.employee;

import java.security.Principal;

import javax.validation.Valid;

import com.DTeam.eshop.entities.Address;
import com.DTeam.eshop.entities.Employee;
import com.DTeam.eshop.services.AddressService;
import com.DTeam.eshop.services.EmployeeService;
import com.DTeam.eshop.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class EmployeeModuleController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private AddressService addressService;

    @Autowired
    private UserService userService;

    @GetMapping("/employee/profile")
    public String edit(Model model, Principal principal) {
        String email = principal.getName();
        if (employeeService.isEmployeeExist(email)) {
            Employee employee= employeeService.getByEmail(email);
            model.addAttribute("employee", employee);
            if (employee.getAddress() == null) {
                Address address = new Address();
                model.addAttribute("address", address);
            } else {
                Address address = addressService.getByEmployeeId(employee.getEmployeeId());
                model.addAttribute("address", address);
            }
            return "views/employee/profile";
        } else {
            Employee employee = new Employee();
            Address address = new Address();
            model.addAttribute("employee", employee);
            model.addAttribute("address", address);
            return "views/employee/profile";
        }
    }

    @PostMapping("/employee/profile")
    public String edit(@Valid Employee employee, BindingResult bindingResult, @Valid Address address,
            BindingResult bindingResult2, Principal principal) {
        if (bindingResult.hasErrors()) {
            return "views/employee/profile";
        }
        if (bindingResult2.hasErrors()) {
            return "views/employee/profile";
        }
        String email = principal.getName();
        employee.setUser(userService.get(email));
        addressService.save(address);
        employee.setAddress(address);
        employeeService.save(employee);
        return "redirect:/customer/profile";
    }
}
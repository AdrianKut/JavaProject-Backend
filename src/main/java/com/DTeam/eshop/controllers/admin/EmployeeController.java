package com.DTeam.eshop.controllers.admin;

import java.util.List;

import com.DTeam.eshop.entities.Address;
import com.DTeam.eshop.entities.Employee;
import com.DTeam.eshop.entities.User;
import com.DTeam.eshop.services.AddressService;
import com.DTeam.eshop.services.EmployeeService;
import com.DTeam.eshop.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private UserService userService;

    @Autowired
    private AddressService addressService;

    @GetMapping("/admin/employee/list")
    public String getAll(Model model){
        List<Employee> employeeList = employeeService.listAll();
        model.addAttribute("employeeList", employeeList);
        return "views/admin/employee/list";
    }

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

    @PostMapping("/admin/employee/add")
    public String add(Employee employee,
        @RequestParam(name = "users")User user,
        @RequestParam(name = "addresses")Address address){
        employee.setUser(user);
        employee.setAddress(address);
        employeeService.save(employee);
        return "redirect:/admin/employee/list";
    }

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

    @PostMapping("/admin/employee/edit/{id}")
    public String edit(@PathVariable(name = "id")Long id, Employee employee,
        @RequestParam(name = "users")User user,
        @RequestParam(name = "addresses")Address address){
        employee.setUser(user);
        employee.setAddress(address);
        employee.setEmployeeId(id);
        employeeService.save(employee);
        return "redirect:/admin/employee/list";
    }

    @GetMapping("/admin/employee/delete/{id}")
    public String delete(@PathVariable(name = "id")Long id){
        employeeService.delete(id);
        return "redirect:/admin/employee/list";
    }
}
package com.DTeam.eshop.controllers.employee;

import java.util.List;

import com.DTeam.eshop.entities.Order;
import com.DTeam.eshop.services.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EmployeeOrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/employee/order/list")
    public String getAll(Model model){
        List<Order> orderList = orderService.getOrderByStatus();
        model.addAttribute("orderList",orderList);
        return "views/employee/list";
    }

}
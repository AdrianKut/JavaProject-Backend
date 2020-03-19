package com.DTeam.eshop.controllers.employee;

import java.util.List;

import com.DTeam.eshop.entities.Order;
import com.DTeam.eshop.services.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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

    @GetMapping("/employee/order/edit/{id}")
    public String edit(Model model,@PathVariable(name = "id") long id){
        Order order = orderService.get(id);
        model.addAttribute("order", order);
        return "views/employee/edit";
    }

    @PostMapping("/employee/order/edit/{id}")
    public String edit(@PathVariable(name = "id") long id,
    Order order){
        Order currnetOrder = orderService.get(id);
        currnetOrder.setShipmentDate(order.getShipmentDate());
        currnetOrder.setOrderStatus(order.getOrderStatus());
        orderService.saveEdit(currnetOrder);
        return "redirect:/employee/order/list";
    }

}
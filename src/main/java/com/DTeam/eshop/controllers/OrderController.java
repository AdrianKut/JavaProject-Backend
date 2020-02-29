package com.DTeam.eshop.controllers;

import java.util.List;

import com.DTeam.eshop.entities.Order;
import com.DTeam.eshop.services.OrderService;
import com.DTeam.eshop.utilities.CustomErrorType;
import org.springframework.http.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/api")
public class OrderController {

    @Autowired private OrderService orderService;

    //Retrieve all orders
    @GetMapping(value="/orders")
    public ResponseEntity<List<Order>> getOrders() {
        List<Order> orders = orderService.listAll();
        if(orders.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Order>>(orders, HttpStatus.OK);
    }

    //Retrieve single order
    @GetMapping(value = "/orders/{id}")
    public ResponseEntity<?> getOrder(@PathVariable("id")Long id){
        if(orderService.isOrderExist(id)){
            Order order = orderService.get(id);
            return new ResponseEntity<Order>(order, HttpStatus.OK);
        }
        return new ResponseEntity<>(new CustomErrorType("Order with id " + id + " not found"), HttpStatus.NOT_FOUND);
    }

    //Create a user
    @PostMapping(value="/orders")
    public ResponseEntity<?> createOrder(@RequestBody Order order, UriComponentsBuilder ucBuilder) {
        Long id = order.getOrderId();
        if(orderService.isOrderExist(id)){
            return new ResponseEntity<>(new CustomErrorType("Unable to create. A order with id " + id + 
            " already exist."), HttpStatus.CONFLICT);
        }
        orderService.save(order);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api/orders/{id}").buildAndExpand(order.getOrderId()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

    //Update a order
    @PutMapping("/orders/{id}")
    public ResponseEntity<?> updateOrder(@PathVariable("id")Long orderId, @RequestBody Order order){
        if(orderService.isOrderExist(orderId)){
            Order currentOrder = orderService.get(orderId);
            currentOrder.setPurchaseDate(order.getPurchaseDate());
            currentOrder.setShipmentDate(order.getShipmentDate());
            currentOrder.setOrderStatus(order.getOrderStatus());
            orderService.save(currentOrder);

            return new ResponseEntity<Order>(currentOrder, HttpStatus.OK);
        }

        return new ResponseEntity<>(new CustomErrorType("Unable to update. Order with id " + orderId + " not found."),
        HttpStatus.NOT_FOUND);
    }

    //Delete a order
    @DeleteMapping("/orders/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable("id")Long orderId){
        if(orderService.isOrderExist(orderId)){
            orderService.delete(orderId);
            return new ResponseEntity<Order>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(new CustomErrorType("Unable to delete. Order with id " + orderId + " not found"),
        HttpStatus.NOT_FOUND);
    }


}
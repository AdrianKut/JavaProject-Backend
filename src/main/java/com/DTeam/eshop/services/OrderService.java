package com.DTeam.eshop.services;

import java.util.List;

import com.DTeam.eshop.entities.Customer;
import com.DTeam.eshop.entities.Order;
import com.DTeam.eshop.entities.Product;
import com.DTeam.eshop.repositories.OrderRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired private OrderRepository orderRepository;
    
    public List<Order> listAll(){
        return orderRepository.findAll();
    }

    public Order save(Order order,List<Product> product){
        order.setProducts(product);
        return orderRepository.save(order);
    }

    public Order saveEdit(Order order){
        return orderRepository.save(order);
    }

    public Order get(Long id){
    return orderRepository.findById(id).get();
    }

    public Order delete(Long id){
        Order order = get(id);
        orderRepository.deleteById(id);
        return order;
    }

    public Boolean isOrderExist(Long id){
        return orderRepository.existsById(id);
    }

    public List<Order> getByCustomer(Customer customer){
        return orderRepository.findByCustomer(customer);
    }

    public List<Order> getOrderByStatus(){
        return orderRepository.findByOrderStatusNotLike("Wysłane");
    }
    


}
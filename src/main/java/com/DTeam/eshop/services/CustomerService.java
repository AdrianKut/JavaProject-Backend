package com.DTeam.eshop.services;

import java.util.List;

import com.DTeam.eshop.entities.Customer;
import com.DTeam.eshop.repositories.CustomerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired private CustomerRepository customerRepository;

    public List<Customer> listAll(){
        return customerRepository.findAll();
    }

    public void save(Customer customer){
        customerRepository.save(customer);
    }

    public Customer get(Long id){
        return customerRepository.findById(id).get();
    }

    public void delete(Long id){
        customerRepository.deleteById(id);
    }

    public Boolean isCustomerExist(Long id){
        return customerRepository.existsById(id);
    }
}
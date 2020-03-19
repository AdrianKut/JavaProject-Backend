package com.DTeam.eshop.services;

import java.util.List;

import com.DTeam.eshop.entities.Customer;
import com.DTeam.eshop.repositories.CustomerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public List<Customer> listAll() {
        return customerRepository.findAll();
    }

    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    public Customer get(Long id) {
        return customerRepository.findById(id).get();
    }

    public Customer delete(Long id) {
        Customer customer = get(id);
        customerRepository.deleteById(id);
        return customer;
    }

    public Boolean isCustomerExist(String email) {
        return customerRepository.existsByUserEmail(email);
    }

    public Customer getByEmail(String email) {
        return customerRepository.findByUserEmail(email);
    }
}

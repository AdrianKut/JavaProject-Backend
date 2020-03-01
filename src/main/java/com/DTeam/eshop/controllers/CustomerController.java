package com.DTeam.eshop.controllers;

import java.util.List;

import com.DTeam.eshop.entities.Customer;
import com.DTeam.eshop.services.CustomerService;
import com.DTeam.eshop.utilities.CustomErrorType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
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
public class CustomerController {

    @Autowired private CustomerService customerService;

    //Retrieve all customers
    @GetMapping("/customers")
    public ResponseEntity<List<Customer>> getCustomers(){
        List<Customer> customers = customerService.listAll();
        if(customers.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Customer>>(customers, HttpStatus.OK);
    }

    //Retrieve single customer
    @GetMapping("/customers/{id}")
    public ResponseEntity<?> getCustomer(@PathVariable("id")Long customerId){
        if(!customerService.isCustomerExist(customerId)){
            return new ResponseEntity<>(new CustomErrorType("Customer with id " + customerId + " not found."),HttpStatus.NOT_FOUND);
        }
        Customer customer = customerService.get(customerId);
        return new ResponseEntity<Customer>(customer, HttpStatus.OK);
    }

    //Create a customer
    @PostMapping("/customers")
    public ResponseEntity<?> createCustomer(@RequestBody Customer customer, UriComponentsBuilder urBuilder){
        Long id = customer.getCustomerId();
        if(id != null){
            return new ResponseEntity<>(new CustomErrorType("Unable to create. Customer with id " + id +
             " already exist."), HttpStatus.CONFLICT);
        }
        customerService.save(customer);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(urBuilder.path("/api/customers/{id}").buildAndExpand(customer.getCustomerId()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

    //Update a customer
    @PutMapping("/customers/{id}")
    public ResponseEntity<?> updateCustomer(@PathVariable("id")Long customerId, @RequestBody Customer customer){
        if(!customerService.isCustomerExist(customerId)){
            return new ResponseEntity<>(new CustomErrorType("Unable to update. Customer with id " + customerId + " not found."),
            HttpStatus.NOT_FOUND);
        }
        Customer currentCustomer = customerService.get(customerId);
        currentCustomer.setName(customer.getName());
        currentCustomer.setSurname(customer.getSurname());
        currentCustomer.setPhoneNumber(customer.getPhoneNumber());
        customerService.save(currentCustomer);
        return new ResponseEntity<Customer>(currentCustomer, HttpStatus.OK);
    }

    //Delete a customer
    @DeleteMapping("/customers/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable("id")Long customerId){
        if(!customerService.isCustomerExist(customerId)){
            return new ResponseEntity<>(new CustomErrorType("Unable to delete. Customer with id " + customerId + " not found."),
            HttpStatus.NOT_FOUND);
        }
        customerService.delete(customerId);
        return new ResponseEntity<Customer>(HttpStatus.NO_CONTENT);
    }
}
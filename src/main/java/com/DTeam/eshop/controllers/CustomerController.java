package com.DTeam.eshop.controllers;

import java.util.List;

import com.DTeam.eshop.entities.Address;
import com.DTeam.eshop.entities.Customer;
import com.DTeam.eshop.services.AddressService;
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
    @Autowired private AddressService addressService;

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

      //Retrieve a address
      @GetMapping("/customers/{id}/addresses")
      public ResponseEntity<?> getAddress(@PathVariable("id")Long customerId){
          if(!customerService.isCustomerExist(customerId)){
              return new ResponseEntity<>(new CustomErrorType("Customer with id " + customerId + " not found."), HttpStatus.NOT_FOUND);
          }
          Customer customer = customerService.get(customerId);
          if(customer.getAddress() == null){
              return new ResponseEntity<>(new CustomErrorType("Customer with id " + customerId + " has no address assigned yet."), HttpStatus.NOT_FOUND); 
          }
          Address address = customer.getAddress();
          return new ResponseEntity<Address>(address, HttpStatus.OK);
      }
  
      //Create a address
      @PostMapping("/customers/{id}/addresses")
      public ResponseEntity<?> createAdress(@PathVariable("id")Long customerId, @RequestBody Address address, UriComponentsBuilder ucBuilder){
          if(!customerService.isCustomerExist(customerId)){
              return new ResponseEntity<>(new CustomErrorType("Unable to create. Customer with id " + customerId + " not found."),
              HttpStatus.NOT_FOUND);
          }
          Customer customer = customerService.get(customerId);
          if(customer.getAddress() != null){
              return new ResponseEntity<>(new CustomErrorType("Unable to create. Customer with id " + customerId + " has already address."),
              HttpStatus.CONFLICT);
          }
          addressService.save(address);
          customer.setAddress(address);
          customerService.save(customer);
          HttpHeaders headers = new HttpHeaders();
          headers.setLocation(ucBuilder.path("/api/addresses/{id}").buildAndExpand(address.getAddressId()).toUri());
          return new ResponseEntity<String>(headers, HttpStatus.CREATED);
      }
      
      //Update a address
      @PutMapping("/customers/{customerid}/addresses/{addressid}")
      public ResponseEntity<?> updateAdress(@PathVariable("customerid")Long customerId, 
      @PathVariable("addressid")Long addressId, @RequestBody Address address){
          if(!customerService.isCustomerExist(customerId)){
              return new ResponseEntity<>(new CustomErrorType("Unable to update. Customer with id " + customerId + " not found."),
              HttpStatus.NOT_FOUND);
          }
          if(!addressService.isAddressExist(addressId)){
              return new ResponseEntity<>(new CustomErrorType("Unable to update. Address with id " + addressId + " not found."),
              HttpStatus.NOT_FOUND); 
          }
          Address currentAddress = addressService.get(addressId);
          currentAddress.setStreet(address.getStreet());
          currentAddress.setHouseNumber(address.getHouseNumber());
          currentAddress.setFlatNumber(address.getFlatNumber());
          currentAddress.setPostcode(address.getPostcode());
          currentAddress.setCity(address.getCity());
          addressService.save(currentAddress);
          return new ResponseEntity<Address>(currentAddress, HttpStatus.OK);
      }
      
      //Create the association
      @PostMapping("/customers/{customerid}/addresses/{addressid}")
      public ResponseEntity<?> associateAdress(@PathVariable("customerid")Long customerId, 
      @PathVariable("addressid")Long addressId){
          if(!customerService.isCustomerExist(customerId)){
              return new ResponseEntity<>(new CustomErrorType("Unable to associate. Customer with id " + customerId + " not found."),
              HttpStatus.NOT_FOUND);
          }
          if(!addressService.isAddressExist(addressId)){
              return new ResponseEntity<>(new CustomErrorType("Unable to associate. Address with id " + addressId + " not found."),
              HttpStatus.NOT_FOUND); 
          }
          Customer customer = customerService.get(customerId);
          if(customer.getAddress() != null){
              return new ResponseEntity<>(new CustomErrorType("Unable to associate. Customer with id " + customerId + " has already address."),
              HttpStatus.CONFLICT);
          }
          customer.setAddress(addressService.get(addressId));
          customerService.save(customer);
          return new ResponseEntity<Customer>(customer, HttpStatus.CREATED);
      }
}
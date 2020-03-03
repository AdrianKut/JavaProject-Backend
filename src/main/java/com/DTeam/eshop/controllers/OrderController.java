package com.DTeam.eshop.controllers;

import java.util.List;

import com.DTeam.eshop.entities.Address;
import com.DTeam.eshop.entities.Customer;
import com.DTeam.eshop.entities.Order;
import com.DTeam.eshop.services.AddressService;
import com.DTeam.eshop.services.CustomerService;
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

    @Autowired private AddressService addressService;

    @Autowired private CustomerService customerService;

    //Retrieve all orders
    @GetMapping("/orders")
    public ResponseEntity<List<Order>> getOrders() {
        List<Order> orders = orderService.listAll();
        if(orders.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Order>>(orders, HttpStatus.OK);
    }

    //Retrieve single order
    @GetMapping("/orders/{id}")
    public ResponseEntity<?> getOrder(@PathVariable("id")Long id){
        if(orderService.isOrderExist(id)){
            Order order = orderService.get(id);
            return new ResponseEntity<Order>(order, HttpStatus.OK);
        }
        return new ResponseEntity<>(new CustomErrorType("Order with id " + id + " not found."), HttpStatus.NOT_FOUND);
    }

    //Create a user
    @PostMapping("/orders")
    public ResponseEntity<?> createOrder(@RequestBody Order order, UriComponentsBuilder ucBuilder) {
        Long id = order.getOrderId();
        if(id != null){
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
        return new ResponseEntity<>(new CustomErrorType("Unable to delete. Order with id " + orderId + " not found."),
        HttpStatus.NOT_FOUND);
    }

    //Retrieve a address
    @GetMapping("/orders/{id}/addresses")
    public ResponseEntity<?> getAddress(@PathVariable("id")Long orderId){
        if(!orderService.isOrderExist(orderId)){
            return new ResponseEntity<>(new CustomErrorType("Order with id " + orderId + " not found."), HttpStatus.NOT_FOUND);
        }
        Order order = orderService.get(orderId);
        if(order.getAddress() == null){
            return new ResponseEntity<>(new CustomErrorType("Order with id " + orderId + " has no address assigned yet."), HttpStatus.NOT_FOUND); 
        }
        Address address = order.getAddress();
        return new ResponseEntity<Address>(address, HttpStatus.OK);
    }

    //Create a address
    @PostMapping("/orders/{id}/addresses")
    public ResponseEntity<?> createAdress(@PathVariable("id")Long orderId, @RequestBody Address address, UriComponentsBuilder ucBuilder){
        if(!orderService.isOrderExist(orderId)){
            return new ResponseEntity<>(new CustomErrorType("Unable to create. Order with id " + orderId + " not found."),
            HttpStatus.NOT_FOUND);
        }
        Order order = orderService.get(orderId);
        if(order.getAddress() != null){
            return new ResponseEntity<>(new CustomErrorType("Unable to create. Order with id " + orderId + " has already address."),
            HttpStatus.CONFLICT);
        }
        addressService.save(address);
        order.setAddress(address);
        orderService.save(order);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api/addresses/{id}").buildAndExpand(address.getAddressId()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

    //Update a address
    @PutMapping("/orders/{orderid}/addresses/{addressid}")
    public ResponseEntity<?> updateAdress(@PathVariable("orderid")Long orderId, 
    @PathVariable("addressid")Long addressId, @RequestBody Address address){
        if(!orderService.isOrderExist(orderId)){
            return new ResponseEntity<>(new CustomErrorType("Unable to update. Order with id " + orderId + " not found."),
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
    @PostMapping("/orders/{orderid}/addresses/{addressid}")
    public ResponseEntity<?> associateAdress(@PathVariable("orderid")Long orderId, 
    @PathVariable("addressid")Long addressId){
        if(!orderService.isOrderExist(orderId)){
            return new ResponseEntity<>(new CustomErrorType("Unable to associate. Order with id " + orderId + " not found."),
            HttpStatus.NOT_FOUND);
        }
        if(!addressService.isAddressExist(addressId)){
            return new ResponseEntity<>(new CustomErrorType("Unable to associate. Address with id " + addressId + " not found."),
            HttpStatus.NOT_FOUND); 
        }
        Order order = orderService.get(orderId);
        if(order.getAddress() != null){
            return new ResponseEntity<>(new CustomErrorType("Unable to associate. Order with id " + orderId + " has already address."),
            HttpStatus.CONFLICT);
        }
        order.setAddress(addressService.get(addressId));
        orderService.save(order);
        return new ResponseEntity<Order>(order, HttpStatus.CREATED);
    }

    //Retrieve a customers
    @GetMapping("/orders/{id}/customers")
    public ResponseEntity<?> getCustomer(@PathVariable("id")Long orderId){
        if(!orderService.isOrderExist(orderId)){
            return new ResponseEntity<>(new CustomErrorType("Order with id " + orderId + " not found."), HttpStatus.NOT_FOUND);
        }
        Order order = orderService.get(orderId);
        if(order.getCustomer() == null){
            return new ResponseEntity<>(new CustomErrorType("Order with id " + orderId + " has no customer assigned yet."), HttpStatus.NOT_FOUND); 
        }
        Customer customer = order.getCustomer();
        return new ResponseEntity<Customer>(customer, HttpStatus.OK);
    }

    //Create a customer
    @PostMapping("/orders/{id}/customers")
    public ResponseEntity<?> createCustomer(@PathVariable("id")Long orderId, @RequestBody Customer customer, UriComponentsBuilder ucBuilder){
        if(!orderService.isOrderExist(orderId)){
            return new ResponseEntity<>(new CustomErrorType("Unable to create. Order with id " + orderId + " not found."),
            HttpStatus.NOT_FOUND);
        }
        Order order = orderService.get(orderId);
        if(order.getCustomer() != null){
            return new ResponseEntity<>(new CustomErrorType("Unable to create. Order with id " + orderId + " has already customer."),
            HttpStatus.CONFLICT);
        }
        customerService.save(customer);
        order.setCustomer(customer);
        orderService.save(order);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api/customers/{id}").buildAndExpand(customer.getCustomerId()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

    //Update a customer
    @PutMapping("/orders/{orderid}/customers/{customerid}")
    public ResponseEntity<?> updateCustomer(@PathVariable("orderid")Long orderId, 
    @PathVariable("customerid")Long customerId, @RequestBody Customer customer){
        if(!orderService.isOrderExist(orderId)){
            return new ResponseEntity<>(new CustomErrorType("Unable to update. Order with id " + orderId + " not found."),
            HttpStatus.NOT_FOUND);
        }
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


}
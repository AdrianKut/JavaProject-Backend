package com.DTeam.eshop.controllers;

import java.util.List;

import com.DTeam.eshop.entities.Order;
import com.DTeam.eshop.entities.Payment;
import com.DTeam.eshop.services.PaymentService;
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
public class PaymentController {

    @Autowired private PaymentService paymentService;

    //Retrieve all payments
    @GetMapping("/payments")
    public ResponseEntity<List<Payment>> getPayments() {
        List<Payment> payments = paymentService.listAll();
        if(payments.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Payment>>(payments, HttpStatus.OK);
    }

    //Retrieve single payment
    @GetMapping("/payments/{id}")
    public ResponseEntity<?> getPayment(@PathVariable("id")Long paymentId){
        if(paymentService.isPaymentExist(paymentId)){
            Payment payment = paymentService.get(paymentId);
            return new ResponseEntity<Payment>(payment, HttpStatus.OK);
        }
        return new ResponseEntity<>(new CustomErrorType("Payment with id " + paymentId + " not found."), HttpStatus.NOT_FOUND);
    }

      //Create a payment
      @PostMapping("/payments")
      public ResponseEntity<?> createPayment(@RequestBody Payment payment, UriComponentsBuilder ucBuilder) {
          Long id = payment.getPaymentId();
          if(id != null){
              return new ResponseEntity<>(new CustomErrorType("Unable to create. A payment with id " + id + 
              " already exist."), HttpStatus.CONFLICT);
          }
          paymentService.save(payment);
  
          HttpHeaders headers = new HttpHeaders();
          headers.setLocation(ucBuilder.path("/api/payments/{id}").buildAndExpand(payment.getPaymentId()).toUri());
          return new ResponseEntity<String>(headers, HttpStatus.CREATED);
      }

      //Update a payment
    @PutMapping("/payments/{id}")
    public ResponseEntity<?> updatePayment(@PathVariable("id")Long paymentId, @RequestBody Payment payment){
        if(paymentService.isPaymentExist(paymentId)){
            Payment currentPayment = paymentService.get(paymentId);
            currentPayment.setPaymentDate(payment.getPaymentDate());
            currentPayment.setAmount(payment.getAmount());
            currentPayment.setPaymentStatus(payment.getPaymentStatus());
            currentPayment.setPaymentMethod(payment.getPaymentMethod());
            paymentService.save(currentPayment);

            return new ResponseEntity<Payment>(currentPayment, HttpStatus.OK);
        }

        return new ResponseEntity<>(new CustomErrorType("Unable to update. Payment with id " + paymentId + " not found."),
        HttpStatus.NOT_FOUND);
    }

    //Delete a payment
    @DeleteMapping("/payments/{id}")
    public ResponseEntity<?> deletePayment(@PathVariable("id")Long paymentId){
        if(paymentService.isPaymentExist(paymentId)){
            paymentService.delete(paymentId);
            return new ResponseEntity<Payment>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(new CustomErrorType("Unable to delete. Payment with id " + paymentId + " not found."),
        HttpStatus.NOT_FOUND);
    }

    //Retrieve a orders
    @GetMapping("/payments/{id}/orders")
    public ResponseEntity<?> getOrders(@PathVariable("id")Long paymentId){
        if(!paymentService.isPaymentExist(paymentId)){
            return new ResponseEntity<>(new CustomErrorType("Payment with id " + paymentId + " not found."), HttpStatus.NOT_FOUND);
        }
        Payment payment = paymentService.get(paymentId);
        if(payment.getOrder() == null){
            return new ResponseEntity<>(new CustomErrorType("Payment with id " + paymentId + " has no order assigned yet."), HttpStatus.NOT_FOUND); 
        }
        Order order = payment.getOrder();
        return new ResponseEntity<Order>(order, HttpStatus.OK);
    }

}
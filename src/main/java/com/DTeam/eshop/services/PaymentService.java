package com.DTeam.eshop.services;

import java.util.List;

import com.DTeam.eshop.entities.Payment;
import com.DTeam.eshop.repositories.PaymentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    @Autowired private PaymentRepository paymentRepository;
    
    public List<Payment> listAll(){
        return paymentRepository.findAll();
    }

    public void save(Payment payment){
        paymentRepository.save(payment);
    }

    public Payment get(Long id){
        return paymentRepository.findById(id).get();
    }

    public void delete(Long id){
        paymentRepository.deleteById(id);
    }

    public Boolean isPaymentExist(Long id){
        return paymentRepository.existsById(id);
    }
}
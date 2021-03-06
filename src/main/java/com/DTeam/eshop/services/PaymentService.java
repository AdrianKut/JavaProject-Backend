package com.DTeam.eshop.services;

import java.time.LocalDateTime;
import java.util.List;

import com.DTeam.eshop.entities.Payment;
import com.DTeam.eshop.repositories.PaymentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    public List<Payment> listAll() {
        return paymentRepository.findAll();
    }

    public Payment save(Payment payment) {
        return paymentRepository.save(payment);
    }

    public Payment get(Long id) {
        return paymentRepository.findById(id).get();
    }

    public Payment delete(Long id) {
        Payment payment = get(id);
        paymentRepository.deleteById(id);
        return payment;
    }

    public Boolean isPaymentExist(Long id) {
        return paymentRepository.existsById(id);
    }

    public List<Payment> getLastMonth() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime earlier = now.minusMonths(1);
        return paymentRepository.findByPaymentDateAfter(earlier);
    }
}

package com.DTeam.eshop.repositories;

import java.time.LocalDateTime;
import java.util.List;

import com.DTeam.eshop.entities.Payment;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    List<Payment> findByPaymentDateAfter(LocalDateTime date);
}
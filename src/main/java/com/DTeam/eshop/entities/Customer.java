package com.DTeam.eshop.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "customers")
@Data
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "customer_id", nullable = false)
    private Long customerId;
    
    @Column(name = "name", nullable = false, length = 25)
    private String name;

    @Column(name = "surname", nullable = false, length = 25)
    private String surname;

    @Column(name = "phone_number", nullable = false, length = 9)
    private String phoneNumber;

    public Customer(){}
}
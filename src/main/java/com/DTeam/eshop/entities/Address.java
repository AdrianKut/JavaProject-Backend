package com.DTeam.eshop.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;

@Entity
@Table(name = "adresses")
@Data
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "address_id", nullable = false)
    private Long addressId;

    @Column(name = "street", nullable = true, length = 35)
    private String street;

    @Column(name = "house_number", nullable = false)
    private Integer houseNumber;

    @Column(name = "flat_number", nullable = true)
    private Integer flatNumber;

    @Column(name = "postcode", nullable = false, length = 6)
    private String postcode;

    @Column(name = "city", nullable = false, length = 35)
    private String city;

    @OneToMany(mappedBy = "address")
    private List<Employee> employees;

    @OneToMany(mappedBy = "address")
    private List<Customer> customers;

    @OneToMany(mappedBy = "address")
    private List<Order> orders;

    public Address(){}
}
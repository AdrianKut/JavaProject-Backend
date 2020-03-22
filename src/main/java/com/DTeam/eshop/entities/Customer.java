package com.DTeam.eshop.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

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
    @NotEmpty(message = "To pole nie może być puste")
    @Length(max = 25, message = "Możesz wprowadzić maksymalnie 25 znaków")
    private String name;

    @Column(name = "surname", nullable = false, length = 25)
    @NotEmpty(message = "To pole nie może być puste")
    @Length(max = 25, message = "Możesz wprowadzić maksymalnie 25 znaków")
    private String surname;

    @Column(name = "phone_number", nullable = false, length = 9)
    @NotEmpty(message = "To pole nie może być puste")
    @Length(max = 9, message = "Możesz wprowadzić maksymalnie 9 znaków")
    private String phoneNumber;

    @OneToOne
    @JoinColumn(name = "user_email", nullable = true, unique = true)
    private User user;

    @ManyToOne
    @JoinColumn(name = "address_id", nullable = true)
    private Address address;

    @OneToMany(mappedBy = "customer")
    private List<Order> orders;

    public Customer() {
    }

    public Customer(Long customerId, String name, String surname, String phoneNumber) {
        this.customerId = customerId;
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
    }

}

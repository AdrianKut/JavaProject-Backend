package com.DTeam.eshop.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class User {

    @Id
    @Column(name = "email", nullable = false, length = 35)
    private String email;

    @Column(name = "password", nullable = false, length = 60)
    private String password;

    @Column(name = "enabled", nullable = false)
    private Boolean enabled = true;

    @OneToOne(mappedBy = "user")
    private Employee employee;

    @OneToOne(mappedBy = "user")
    private Customer customer;

    @ManyToMany
    @JoinTable(name = "users_roles", joinColumns = {
        @JoinColumn(name = "user_email", referencedColumnName = "email")},
        inverseJoinColumns = {
            @JoinColumn(name = "role_name", referencedColumnName = "name")
        })
    private List<Role> roles;

    public User(){}
}
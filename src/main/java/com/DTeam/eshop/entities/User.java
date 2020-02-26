package com.DTeam.eshop.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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

    public User(){}
}
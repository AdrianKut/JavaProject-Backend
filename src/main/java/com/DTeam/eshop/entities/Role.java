package com.DTeam.eshop.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;

@Entity
@Table(name = "roles")
@Data
public class Role {

    @Id
    @Column(name = "name", nullable = false, length = 25)
    private String name;

    @ManyToMany(mappedBy = "roles")
    @JsonBackReference("users-rolse")
    private List<User> users;

    public Role(){}
}
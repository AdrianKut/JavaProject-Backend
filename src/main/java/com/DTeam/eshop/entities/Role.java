package com.DTeam.eshop.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "roles")
@Data
public class Role {

    @Id
    @Column(name = "name", nullable = false)
    private String name;

    public Role(){}
}
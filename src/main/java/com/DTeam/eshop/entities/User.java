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
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class User {

    @Id
    @Column(name = "email", nullable = false, length = 35)
    private String email;

    @Column(name = "userID", columnDefinition = "bigserial", insertable = false)
    private Integer userId;

    @Column(name = "password", nullable = false, length = 60)
    @NotEmpty(message = "To pole nie może być puste")
    @Length(max = 60, message = "Możesz wprowadzić maksymalnie 60 znaków")
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

    public User() {
    }

    public User(String email, Integer userId, String password, Boolean enabled) {
        this.email = email;
        this.userId = userId;
        this.password = password;
        this.enabled = enabled;
    }
}

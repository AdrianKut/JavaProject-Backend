package com.DTeam.eshop.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Entity
@Table(name = "products")
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "product_id", nullable = false)
    private Long productId;

    @Column(name = "name", nullable = false, length = 35)
    @NotEmpty(message = "To pole nie może być puste")
    @Length(max = 35, message = "Możesz wprowadzić maksymalnie 35 znaków")
    private String name;

    @Column(name = "description", nullable = false, length = 115)
    @NotEmpty(message = "To pole nie może być puste")
    @Length(max = 115, message = "Możesz wprowadzić maksymalnie 115 znaków")
    private String description;

    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "photo", nullable = false, length = 250)
    @NotEmpty(message = "To pole nie może być puste")
    @Length(max = 250, message = "Możesz wprowadzić maksymalnie 250 znaków")
    private String photo;

    @Column(name = "category", nullable = false, length = 50)
    @NotEmpty(message = "To pole nie może być puste")
    @Length(max = 50, message = "Możesz wprowadzić maksymalnie 50 znaków")
    private String category;

    @ManyToMany(mappedBy = "products")
    private List<Order> orders;

    @OneToMany(mappedBy = "product")
    private List<Complaint> complaints;

    public Product() {
    }

    public Product(Long productId, String name, String description, Double price, Integer quantity, String photo, String category) {
        this.productId = productId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.photo = photo;
        this.category = category;
    }
}

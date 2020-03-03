package com.DTeam.eshop.entities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityReference;

import lombok.Data;

@Entity
@Table(name = "orders")
@Data
public class Order{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "order_id", nullable = false)
    private Long orderId;

    @Column(name = "purchase_date", nullable = false)
    private LocalDateTime purchaseDate;

    @Column(name = "shipment_date", nullable = false)
    private LocalDateTime shipmentDate;

    @Column(name = "order_status", nullable = true, length = 20)
    private String orderStatus;

    @ManyToOne
    @JsonIdentityReference
    @JoinColumn(name = "address_id", nullable = true)
    private Address address;

    @ManyToOne
    @JsonIdentityReference
    @JoinColumn(name = "customer_id", nullable = true)
    private Customer customer;

    @OneToMany(mappedBy = "order")
    @JsonBackReference("payment-order")
    private List<Payment> payments;

    @ManyToMany
    @JoinTable(name = "orders_products", joinColumns = {
        @JoinColumn(name = "order_id", referencedColumnName = "order_id")},
        inverseJoinColumns = {
            @JoinColumn(name = "product_id", referencedColumnName = "product_id")
        })
    private List<Product> products;

    @OneToMany(mappedBy = "order")
    @JsonBackReference("complaint-order")
    private List<Complaint> complaints;

    public Order(){}

    public String getPurchaseDate() {      
        if(purchaseDate !=null){
            return purchaseDate.toString();
        }else{
            return "";
        }
    }

    public void setPurchaseDate(String purchaseDate) {       
        LocalDateTime dataTime = LocalDateTime.parse(purchaseDate,DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        this.purchaseDate = dataTime;
    }

    public String getShipmentDate() {      
        if(shipmentDate !=null){
            return shipmentDate.toString();
        }else{
            return "";
        }
    }

    public void setShipmentDate(String shipmentDate) {       
        LocalDateTime dataTime = LocalDateTime.parse(shipmentDate,DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        this.shipmentDate = dataTime;
    }
}
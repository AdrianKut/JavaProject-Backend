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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

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

    @Column(name = "shipment_date", nullable = true)
    private LocalDateTime shipmentDate;

    @Column(name = "order_status", nullable = true, length = 20)
    @NotEmpty(message = "To pole nie może być puste")
    @Length(max = 20, message = "Możesz wprowadzić maksymalnie 20 znaków")
    private String orderStatus;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = true)
    private Customer customer;

    @OneToOne
    @JoinColumn(name = "payment_id", nullable = true)
    private Payment payment;

    @ManyToMany
    @JoinTable(name = "orders_products", joinColumns = {
        @JoinColumn(name = "order_id", referencedColumnName = "order_id")},
        inverseJoinColumns = {
            @JoinColumn(name = "product_id", referencedColumnName = "product_id")
        })
    private List<Product> products;

    @OneToMany(mappedBy = "order")
    private List<Complaint> complaints;

    public Order(){}

    public Order(Long orderId, LocalDateTime purchaseDate) {
        this.orderId = orderId;
        this.purchaseDate = purchaseDate;
    }


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
        if(!shipmentDate.equals("")){
            LocalDateTime dataTime = LocalDateTime.parse(shipmentDate,DateTimeFormatter.ISO_LOCAL_DATE_TIME);
            this.shipmentDate = dataTime;
        }
    }
}
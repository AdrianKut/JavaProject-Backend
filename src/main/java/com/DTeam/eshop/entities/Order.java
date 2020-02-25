package com.DTeam.eshop.entities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "orders")
@Data
public class Order{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "order_id", nullable = false)
    private Long orderId;

    @Column(name = "purchase_date", nullable = false, length = 20)
    private LocalDateTime purchaseDate;

    @Column(name = "shipment_date", nullable = false, length = 20)
    private LocalDateTime shipmentDate;

    @Column(name = "order_status", nullable = true, length = 100)
    private String orderStatus;

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
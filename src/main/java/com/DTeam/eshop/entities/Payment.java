package com.DTeam.eshop.entities;

import java.math.BigDecimal;
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
@Table(name = "payments")
@Data
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "payment_id", nullable = false)
    private Long paymentId;

    @Column(name = "payment_date", nullable = false, length = 20)
    private LocalDateTime paymentDate;
    
    @Column(name = "amount", nullable = false, length = 20)
    private BigDecimal amount;

    @Column(name = "payment_status", nullable = false, length = 25)
    private String paymentStatus;

    @Column(name = "payment_method", nullable = false, length = 25)
    private String paymentMethod;

    public Payment(){}

    public String getPaymentDate() {      
        if(paymentDate !=null){
            return paymentDate.toString();
        }else{
            return "";
        }
    }

    public void setPaymentDate(String paymentDate) {       
        LocalDateTime dataTime = LocalDateTime.parse(paymentDate,DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        this.paymentDate = dataTime;
    }
}
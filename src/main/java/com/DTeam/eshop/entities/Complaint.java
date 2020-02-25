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
@Table(name = "complaints")
@Data
public class Complaint {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "complaint_id", nullable = false)
    private Long complaintId;

    @Column(name = "notification_date", nullable = false)
    private LocalDateTime notificationDate;

    @Column(name = "description", nullable = false, length = 100)
    private String description;

    @Column(name = "complaint_status", nullable = false, length = 20)
    private String complaintStatus;

    public Complaint(){}

    public String getNotificationDate() {      
        if(notificationDate !=null){
            return notificationDate.toString();
        }else{
            return "";
        }
    }

    public void setNotificationDate(String notificationDate) {       
        LocalDateTime dataTime = LocalDateTime.parse(notificationDate,DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        this.notificationDate = dataTime;
    }
    
}
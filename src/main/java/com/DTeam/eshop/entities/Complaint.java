package com.DTeam.eshop.entities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

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

    @Column(name = "description", nullable = false, length = 200)
    @NotEmpty(message = "To pole nie może być puste")
    @Length(max = 200, message = "Możesz wprowadzić maksymalnie 200 znaków")
    private String description;

    @Column(name = "complaint_status", nullable = false, length = 20)
    @NotEmpty(message = "To pole nie może być puste")
    @Length(max = 20, message = "Możesz wprowadzić maksymalnie 20 znaków")
    private String complaintStatus;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = true)
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = true)
    private Product product;

    public Complaint(){}

    public Complaint(Long complaintId, LocalDateTime notificationDate, String description, String complaintStatus) {
        this.complaintId = complaintId;
        this.notificationDate = notificationDate;
        this.description = description;
        this.complaintStatus = complaintStatus;
    }


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
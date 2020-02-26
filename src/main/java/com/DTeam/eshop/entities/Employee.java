package com.DTeam.eshop.entities;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "employees")
@Data
public class Employee {

    @Id    
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "employee_id", nullable = false)
    private Long employeeId;

    @Column(name = "name", nullable = false, length = 25)
    private String name;

    @Column(name = "surname", nullable = false, length = 25)
    private String surname;

    @Column(name = "employment_date", nullable = false)
    private LocalDate employmentDate;

    @Column(name = "base_pay", nullable = false)
    private BigDecimal basePay;

    @Column(name = "extra_pay", nullable = false)
    private BigDecimal extraPay;

    @Column(name = "position", nullable = false, length = 25)
    private String position;

    @OneToOne
    @JoinColumn(name = "user_email", nullable = true, unique = true)
    private User user;

    public Employee(){}

    public String getEmployemntDate() {
        if (employmentDate!= null) {
           return employmentDate.toString();
       } else {
           return "";
       }
   }

   public void setEmploymentDate(String employmentDate) {
       LocalDate date = LocalDate.parse(employmentDate, DateTimeFormatter.ISO_LOCAL_DATE);
       this.employmentDate = date;
   }
}
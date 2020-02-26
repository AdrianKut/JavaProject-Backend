package com.DTeam.eshop.entities;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "surname", nullable = false)
    private String surname;

    @Column(name = "employment_date", nullable = false)
    private LocalDate employmentDate;

    @Column(name = "base_pay", nullable = false)
    private BigDecimal basePay;

    @Column(name = "extra_pay", nullable = false)
    private BigDecimal extraPay;

    @Column(name = "position", nullable = false)
    private String position;

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
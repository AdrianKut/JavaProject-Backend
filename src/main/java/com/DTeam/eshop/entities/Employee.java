package com.DTeam.eshop.entities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

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
    @NotEmpty(message = "To pole nie może być puste")
    @Length(max = 25, message = "Możesz wprowadzić maksymalnie 25 znaków")
    private String name;

    @Column(name = "surname", nullable = false, length = 25)
    @NotEmpty(message = "To pole nie może być puste")
    @Length(max = 25, message = "Możesz wprowadzić maksymalnie 25 znaków")
    private String surname;

    @Column(name = "employment_date", nullable = false)
    private LocalDate employmentDate;

    @Column(name = "base_pay", nullable = false)
    private Double basePay;

    @Column(name = "extra_pay", nullable = true)
    private Double extraPay;

    @Column(name = "position", nullable = false, length = 25)
    @NotEmpty(message = "To pole nie może być puste")
    @Length(max = 25, message = "Możesz wprowadzić maksymalnie 25 znaków")
    private String position;

    @OneToOne
    @JoinColumn(name = "user_email", nullable = true, unique = true)
    private User user;

    @ManyToOne
    @JoinColumn(name = "address_id", nullable = true)
    private Address address;

    public Employee(){}

    public Employee(Long employeeId, String name, String surname, LocalDate employmentDate, Double basePay, String position) {
        this.employeeId = employeeId;
        this.name = name;
        this.surname = surname;
        this.employmentDate = employmentDate;
        this.basePay = basePay;
        this.position = position;
    }

    public String getEmploymentDate() {
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
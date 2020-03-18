package com.DTeam.eshop.services;

import java.util.List;

import com.DTeam.eshop.entities.Employee;
import com.DTeam.eshop.repositories.EmployeeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    @Autowired private EmployeeRepository employeeRepository;

    public List<Employee> listAll(){
        return employeeRepository.findAll();
    }

    public void save(Employee employee){
        employeeRepository.save(employee);
    }

    public Employee get(Long id){
        return employeeRepository.findById(id).get();
    }

    public void delete(Long id){
        employeeRepository.deleteById(id);
    }

    public Boolean isEmployeeExist(String email){
        return employeeRepository.existsByUserEmail(email);
    }

    public Employee getByEmail(String email){
        return employeeRepository.findByUserEmail(email);
    }
}
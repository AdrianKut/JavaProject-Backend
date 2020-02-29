package com.DTeam.eshop.controllers;

import java.util.List;

import com.DTeam.eshop.entities.Employee;
import com.DTeam.eshop.services.EmployeeService;
import com.DTeam.eshop.utilities.CustomErrorType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/api")
public class EmployeeController {

    @Autowired private EmployeeService employeeService;

    //Retrieve all employees
    @GetMapping(value = "/employees")
    public ResponseEntity<List<Employee>> getEmployees(){
        List<Employee> employees = employeeService.listAll();
        if(employees.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Employee>>(employees, HttpStatus.OK);
    }
    
    //Retrieve single employee
    @GetMapping(value = "/employees/{id}")
    public ResponseEntity<?> getEmployee(@PathVariable("id")Long employeeId){
        if(employeeService.isEmployeeExist(employeeId)){
            Employee employee = employeeService.get(employeeId);
            return new ResponseEntity<Employee>(employee, HttpStatus.OK);
        }
        return new ResponseEntity<>(new CustomErrorType("Employee with id " + employeeId + " not found."), HttpStatus.NOT_FOUND);
    }

    //Create a employee
    @PostMapping(value = "/employees")
    public ResponseEntity<?> createEmployee(@RequestBody Employee employee, UriComponentsBuilder ucBuilder){
        Long id = employee.getEmployeeId();
        if(id != null){
            return new ResponseEntity<>(new CustomErrorType("Unable to create. A employee with id " + id 
            + " already exist."), HttpStatus.CONFLICT);
        }
        employeeService.save(employee);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api/employees/{id}").buildAndExpand(employee.getEmployeeId()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

    //Update a employee
    @PutMapping("/employees/{id}")
    public ResponseEntity<?> updateEmpolyee(@PathVariable("id")Long employeeId, @RequestBody Employee employee){
        if(employeeService.isEmployeeExist(employeeId)){
            Employee currentEmployee = employeeService.get(employeeId);
            currentEmployee.setName(employee.getName());
            currentEmployee.setSurname(employee.getSurname());
            currentEmployee.setEmploymentDate(employee.getEmployemntDate());
            currentEmployee.setBasePay(employee.getBasePay());
            currentEmployee.setExtraPay(employee.getExtraPay());
            currentEmployee.setPosition(employee.getPosition());
            employeeService.save(currentEmployee);

            return new ResponseEntity<Employee>(currentEmployee, HttpStatus.OK);
        }
        return new ResponseEntity<>(new CustomErrorType("Unable to update. Employee with id " + employeeId + " not found."),
        HttpStatus.NOT_FOUND);
    }

    //Delete a employee
    @DeleteMapping("/employees/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable("id")Long employeeId){
        if(employeeService.isEmployeeExist(employeeId)){
            employeeService.delete(employeeId);
            return new ResponseEntity<Employee>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(new CustomErrorType("Unable to delete. Employee with id " + employeeId + " not found."),
        HttpStatus.NOT_FOUND);
    }
}
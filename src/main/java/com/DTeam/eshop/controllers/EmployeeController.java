package com.DTeam.eshop.controllers;

import java.util.List;

import com.DTeam.eshop.entities.Address;
import com.DTeam.eshop.entities.Employee;
import com.DTeam.eshop.entities.User;
import com.DTeam.eshop.services.AddressService;
import com.DTeam.eshop.services.EmployeeService;
import com.DTeam.eshop.services.UserService;
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
    @Autowired private AddressService addressService;
    @Autowired private UserService userService;

    //Retrieve all employees
    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> getEmployees(){
        List<Employee> employees = employeeService.listAll();
        if(employees.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Employee>>(employees, HttpStatus.OK);
    }
    
    //Retrieve single employee
    @GetMapping("/employees/{id}")
    public ResponseEntity<?> getEmployee(@PathVariable("id")Long employeeId){
        if(employeeService.isEmployeeExist(employeeId)){
            Employee employee = employeeService.get(employeeId);
            return new ResponseEntity<Employee>(employee, HttpStatus.OK);
        }
        return new ResponseEntity<>(new CustomErrorType("Employee with id " + employeeId + " not found."), HttpStatus.NOT_FOUND);
    }

    //Create a employee
    @PostMapping("/employees")
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
            currentEmployee.setEmploymentDate(employee.getEmploymentDate());
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

    //Retrieve a address
    @GetMapping("/employees/{id}/addresses")
    public ResponseEntity<?> getAddress(@PathVariable("id")Long employeeId){
        if(!employeeService.isEmployeeExist(employeeId)){
            return new ResponseEntity<>(new CustomErrorType("Employee with id " + employeeId + " not found."), HttpStatus.NOT_FOUND);
        }
        Employee employee = employeeService.get(employeeId);
        if(employee.getAddress() == null){
            return new ResponseEntity<>(new CustomErrorType("Employee with id " + employeeId + " has no address assigned yet."), HttpStatus.NOT_FOUND); 
        }
        Address address = employee.getAddress();
        return new ResponseEntity<Address>(address, HttpStatus.OK);
    }

    //Create a address
    @PostMapping("/employees/{id}/addresses")
    public ResponseEntity<?> createAdress(@PathVariable("id")Long employeeId, @RequestBody Address address, UriComponentsBuilder ucBuilder){
        if(!employeeService.isEmployeeExist(employeeId)){
            return new ResponseEntity<>(new CustomErrorType("Unable to create. Employee with id " + employeeId + " not found."),
            HttpStatus.NOT_FOUND);
        }
        Employee employee = employeeService.get(employeeId);
        if(employee.getAddress() != null){
            return new ResponseEntity<>(new CustomErrorType("Unable to create. Employee with id " + employeeId + " has already address."),
            HttpStatus.CONFLICT);
        }
        addressService.save(address);
        employee.setAddress(address);
        employeeService.save(employee);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api/addresses/{id}").buildAndExpand(employee.getEmployeeId()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }
    
    //Update a address
    @PutMapping("/employees/{employeeid}/addresses/{addressid}")
    public ResponseEntity<?> updateAdress(@PathVariable("employeeid")Long employeeId, 
    @PathVariable("addressid")Long addressId, @RequestBody Address address, UriComponentsBuilder ucBuilder){
        if(!employeeService.isEmployeeExist(employeeId)){
            return new ResponseEntity<>(new CustomErrorType("Unable to update. Employee with id " + employeeId + " not found."),
            HttpStatus.NOT_FOUND);
        }
        if(!addressService.isAddressExist(addressId)){
            return new ResponseEntity<>(new CustomErrorType("Unable to update. Address with id " + addressId + " not found."),
            HttpStatus.NOT_FOUND); 
        }
        Address currentAddress = addressService.get(addressId);
        currentAddress.setStreet(address.getStreet());
        currentAddress.setHouseNumber(address.getHouseNumber());
        currentAddress.setFlatNumber(address.getFlatNumber());
        currentAddress.setPostcode(address.getPostcode());
        currentAddress.setCity(address.getCity());
        addressService.save(currentAddress);
        return new ResponseEntity<Address>(currentAddress, HttpStatus.OK);
    }
    
    //Create the association
    @PostMapping("/employees/{employeeid}/addresses/{addressid}")
    public ResponseEntity<?> associateAdress(@PathVariable("employeeid")Long employeeId, 
    @PathVariable("addressid")Long addressId, @RequestBody Address address, UriComponentsBuilder ucBuilder){
        if(!employeeService.isEmployeeExist(employeeId)){
            return new ResponseEntity<>(new CustomErrorType("Unable to associate. Employee with id " + employeeId + " not found."),
            HttpStatus.NOT_FOUND);
        }
        if(!addressService.isAddressExist(addressId)){
            return new ResponseEntity<>(new CustomErrorType("Unable to associate. Address with id " + addressId + " not found."),
            HttpStatus.NOT_FOUND); 
        }
        Employee employee = employeeService.get(employeeId);
        if(employee.getAddress() != null){
            return new ResponseEntity<>(new CustomErrorType("Unable to associate. Employee with id " + employeeId + " has already address."),
            HttpStatus.CONFLICT);
        }
        employee.setAddress(addressService.get(addressId));
        employeeService.save(employee);
        return new ResponseEntity<Employee>(employee, HttpStatus.CREATED);
    }

    //Retrieve a user
    @GetMapping("/employees/{id}/users")
    public ResponseEntity<?> getUsers(@PathVariable("id")Long employeeId){
        if(!employeeService.isEmployeeExist(employeeId)){
            return new ResponseEntity<>(new CustomErrorType("Employee with id " + employeeId +  " not found."),
            HttpStatus.NOT_FOUND);
        }
        Employee employee = employeeService.get(employeeId);
        if(employee.getUser() == null){
            return new ResponseEntity<>(new CustomErrorType("Employee with id " + employeeId + " has no user assigned yet."),
            HttpStatus.NOT_FOUND);
        }
        User user = employee.getUser();
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    //Create a user
    @PostMapping("/employees/{id}/users")
    public ResponseEntity<?> createUser(@PathVariable("id")Long employeeId, @RequestBody User user, UriComponentsBuilder ucBuilder){
        if(!employeeService.isEmployeeExist(employeeId)){
            return new ResponseEntity<>(new CustomErrorType("Unable to create. Employee with id " + employeeId + " not found."),
            HttpStatus.NOT_FOUND);
        }
        Employee employee = employeeService.get(employeeId);
        if(employee.getUser() != null){
            return new ResponseEntity<>(new CustomErrorType("Unable to create. Employee with id " + employeeId + " has already user."),
            HttpStatus.CONFLICT);
        }
        userService.save(user);
        employee.setUser(user);
        employeeService.save(employee);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api/users/{id}").buildAndExpand(employee.getEmployeeId()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

    //Update a user
    @PutMapping("empolyees/{employeeid}/users/{email}")
    public ResponseEntity<?> updateUser(@PathVariable("employeeid")Long employeeId,
    @PathVariable("email")String email, @RequestBody User user){
        if(!employeeService.isEmployeeExist(employeeId)){
            return new ResponseEntity<>(new CustomErrorType("Unable to update. Employee with id " + employeeId + " not found."),
            HttpStatus.NOT_FOUND);
        }
        if(!userService.isUserExist(email)){
            return new ResponseEntity<>(new CustomErrorType("Unable to update. User with email " + email + " not found."),
            HttpStatus.NOT_FOUND); 
        }
        User currentUser = userService.get(email);
        currentUser.setPassword(user.getPassword());
        currentUser.setEnabled(user.getEnabled());
        userService.save(currentUser);
        return new ResponseEntity<User>(currentUser, HttpStatus.OK);
    }

    //Create the association
    @PostMapping("/employees/{employeeId}/users/{email}")
    public ResponseEntity<?> associateUser(@PathVariable("employeeId")Long employeeId,
    @PathVariable("email")String email, @RequestBody User user){
        if(!employeeService.isEmployeeExist(employeeId)){
            return new ResponseEntity<>(new CustomErrorType("Unable to associate. Employee with id " + employeeId + " not found."),
            HttpStatus.NOT_FOUND);
        }
        if(!userService.isUserExist(email)){
            return new ResponseEntity<>(new CustomErrorType("Unable to associate. User with email " + email + " not found."),
            HttpStatus.NOT_FOUND); 
        }
        Employee employee = employeeService.get(employeeId);
        if(employee.getUser() != null){
            return new ResponseEntity<>(new CustomErrorType("Unable to associate. Employee with id " + employeeId + " has already user."),
            HttpStatus.CONFLICT);
        }
        employee.setUser(user);
        employeeService.save(employee);
        return new ResponseEntity<Employee>(employee, HttpStatus.CREATED);
    }
}
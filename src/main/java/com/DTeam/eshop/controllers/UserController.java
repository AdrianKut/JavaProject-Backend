package com.DTeam.eshop.controllers;

import java.util.List;

import com.DTeam.eshop.entities.Role;
import com.DTeam.eshop.entities.User;
import com.DTeam.eshop.services.RoleService;
import com.DTeam.eshop.services.UserService;
import com.DTeam.eshop.utilities.CustomErrorType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api")
@CrossOrigin(origins="http://localhost:3000")
public class UserController {

    @Autowired private UserService userService;
    @Autowired private RoleService roleService;

    //Retrieve all users
    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers() {
        List<User> users = userService.listAll();
        if(users.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }

    //Retrieve single user
    @GetMapping("/users/{email}")
    public ResponseEntity<?> getUser(@PathVariable("email")String email){
        if(userService.isUserExist(email)){
            User user = userService.get(email);
            return new ResponseEntity<User>(user, HttpStatus.OK);
        }
        return new ResponseEntity<>(new CustomErrorType("User with email " + email + " not found."), HttpStatus.NOT_FOUND);
    }

    //Create a user
    @PostMapping("/users")
    public ResponseEntity<?> createUser(@RequestBody User user, UriComponentsBuilder ucBuilder) {
        String email = user.getEmail();
        if(userService.isUserExist(email)){
            return new ResponseEntity<>(new CustomErrorType("Unable to create. A user with email " + email +
            " already exist."), HttpStatus.CONFLICT);
        }
        userService.save(user);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api/users/{email}").buildAndExpand(user.getEmail()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

    //Update a user
    @PutMapping("/users/{email}")
    public ResponseEntity<?> updateUser(@PathVariable("email")String email, @RequestBody User user){
        if(userService.isUserExist(email)){
            User currentUser = userService.get(email);
            currentUser.setPassword(user.getPassword());
            currentUser.setEnabled(user.getEnabled());
            userService.save(currentUser);

            return new ResponseEntity<User>(currentUser, HttpStatus.OK);
        }

        return new ResponseEntity<>(new CustomErrorType("Unable to update. User with email " + email + " not found."),
        HttpStatus.NOT_FOUND);
    }

    //Delete a user
    @DeleteMapping("/users/{email}")
    public ResponseEntity<?> deleteUser(@PathVariable("email")String email){
        if(userService.isUserExist(email)){
            userService.delete(email);
            return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(new CustomErrorType("Unable to delete. User with email " + email + " not found."),
        HttpStatus.NOT_FOUND);
    }

    //Retieve all roles
    @GetMapping("/users/{email}/roles")
    public ResponseEntity<?> getRoles(@PathVariable("email")String email){
        if(!userService.isUserExist(email)){
            return new ResponseEntity<>(new CustomErrorType("User with email " + email + " not found."), HttpStatus.NOT_FOUND);
        }
        User user = userService.get(email);
        if(user.getRoles().isEmpty()){
            return new ResponseEntity<>(new CustomErrorType("User with email " + email + " has no roles assigned yet."), HttpStatus.NOT_FOUND);
        }
        List<Role> roles = roleService.getByUserEmail(email);
        return new ResponseEntity<List<Role>>(roles, HttpStatus.OK);
    }

    //Create the association
    @PutMapping("/users/{email}/roles")
    public ResponseEntity<?> associateRoles(@PathVariable("email")String email, @RequestBody List<Role> roles){
        if(!userService.isUserExist(email)){
            return new ResponseEntity<>(new CustomErrorType("User with email " + email + " not found."), HttpStatus.NOT_FOUND);
        }
        User user = userService.get(email);
        user.setRoles(roles);
        userService.save(user);
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }
}
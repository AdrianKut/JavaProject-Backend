package com.DTeam.eshop.controllers;

import java.util.List;

import com.DTeam.eshop.entities.Role;
import com.DTeam.eshop.services.RoleService;
import com.DTeam.eshop.utilities.CustomErrorType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/api")
public class RoleController {

    @Autowired private RoleService roleService;

    //Retrieve all roles
    @GetMapping("/roles")
    public ResponseEntity<List<Role>> getRoles(){
        List<Role> roles = roleService.listAll();
        if(roles.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Role>>(roles, HttpStatus.OK);
    }

    //Retrieve single role
	@GetMapping("/role/{name}")
	public ResponseEntity<?> getRole(@PathVariable("name") String name) {
        if(roleService.isRoleExist(name)){
            Role role = roleService.get(name);
            return new ResponseEntity<Role>(role, HttpStatus.OK);
        }
		return new ResponseEntity<>(new CustomErrorType("Role with name " + name 
        + " not found"), HttpStatus.NOT_FOUND);	
    }
    
    //Create a role
    @PostMapping("/role")
	public ResponseEntity<?> createRole(@RequestBody Role role, UriComponentsBuilder ucBuilder) {
        String name = role.getName();
		if (roleService.isRoleExist(name)) {
			return new ResponseEntity<>(new CustomErrorType("Unable to create. A Role with name " + 
			name + " already exist."), HttpStatus.CONFLICT);
		}
		roleService.save(role);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api/roles/{name}").buildAndExpand(role.getName()).toUri());
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}

    //Delete a role
    @DeleteMapping("/role/{name}")
	public ResponseEntity<?> deleteRole(@PathVariable("name") String name) {	     
        if(roleService.isRoleExist(name)){
            roleService.delete(name);
            return new ResponseEntity<Role>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(new CustomErrorType("Unable to delete. Role with name " + name + " not found."),
					HttpStatus.NOT_FOUND);
    }
}
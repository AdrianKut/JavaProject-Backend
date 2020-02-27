package com.DTeam.eshop.controllers;

import java.util.List;

import com.DTeam.eshop.entities.Role;
import com.DTeam.eshop.services.RoleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class RoleController {

    @Autowired private RoleService roleService;

    @GetMapping("/roles")
    public List<Role> getRoles(){
        return roleService.listAll();
    }

    @GetMapping("/role/{name}")
    public Role getRole(@PathVariable String name){
        return roleService.get(name);
    }

    @DeleteMapping("/role/{name}")
    public boolean deleteRole(@PathVariable String name){
       roleService.delete(name);
       return true;
    }

    @PutMapping("/role")
    public Role updateRole(Role role){
        return roleService.save(role);
    }

    @PostMapping("/role")
    public Role createRole(Role role){
        return roleService.save(role);
    }
}
package com.DTeam.eshop.services;

import java.util.List;

import com.DTeam.eshop.entities.Role;
import com.DTeam.eshop.repositories.RoleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    @Autowired private RoleRepository roleRepository;
    
    public List<Role> listAll(){
        return roleRepository.findAll();
    }

    public void save(Role role){
        roleRepository.save(role);
    }

    public Role get(String name){
        return roleRepository.findById(name).get();
    }

    public void delete(String name){
        roleRepository.deleteById(name);
    }
}
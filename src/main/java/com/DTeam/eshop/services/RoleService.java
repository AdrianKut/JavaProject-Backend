package com.DTeam.eshop.services;

import java.util.List;

import com.DTeam.eshop.entities.Role;
import com.DTeam.eshop.repositories.RoleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public List<Role> listAll() {
        return roleRepository.findAll();
    }

    public Role save(Role role) {
        return roleRepository.save(role);
    }

    public Role get(String name) {
        return roleRepository.findById(name).get();
    }

    public Role delete(String name) {
        Role role = get(name);
        roleRepository.deleteById(name);
        return role;
    }

    public void deleteAll() {
        roleRepository.deleteAll();
    }

    public Boolean isRoleExist(String name) {
        return roleRepository.existsById(name);
    }

    public List<Role> getByUserEmail(String email) {
        return roleRepository.findByUsersEmail(email);
    }
}

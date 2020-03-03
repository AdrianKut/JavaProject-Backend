package com.DTeam.eshop.repositories;

import java.util.List;

import com.DTeam.eshop.entities.Role;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, String> {
    public List<Role> findByUsersEmail(String email);
}
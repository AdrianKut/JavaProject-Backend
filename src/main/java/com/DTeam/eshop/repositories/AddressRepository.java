package com.DTeam.eshop.repositories;

import com.DTeam.eshop.entities.Address;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long>{
    public Address findByCustomersCustomerId(Long id);
    public Address findByEmployeesEmployeeId(Long id);
}
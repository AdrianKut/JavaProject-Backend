package com.DTeam.eshop.services;

import java.util.List;

import com.DTeam.eshop.entities.Address;
import com.DTeam.eshop.repositories.AddressRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    @Autowired private AddressRepository addressRepository;

    public List<Address> listAll(){
        return addressRepository.findAll();
    }

    public Address save(Address address){
        return addressRepository.save(address);
    }
    
    public Address get(Long id){
        return addressRepository.findById(id).get();
    }

    public Address delete(Long id){
        Address address = get(id);
        addressRepository.deleteById(id);
        return address;
    }

    public Boolean isAddressExist(Long id){
        return addressRepository.existsById(id);
    }

    public Address getByCustomerId(Long id){
        return addressRepository.findByCustomersCustomerId(id);
    }

    public Address getByEmployeeId(Long id){
        return addressRepository.findByEmployeesEmployeeId(id);
    }
}
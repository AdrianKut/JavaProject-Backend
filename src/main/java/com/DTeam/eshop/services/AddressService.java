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

    public void save(Address address){
        addressRepository.save(address);
    }

    public Address get(Long id){
        return addressRepository.findById(id).get();
    }

    public void delete(Long id){
        addressRepository.deleteById(id);
    }

    public Boolean isAddressExist(Long id){
        return addressRepository.existsById(id);
    }

    public Address getByCustomerId(Long id){
        return addressRepository.findByCustomersCustomerId(id);
    }
}
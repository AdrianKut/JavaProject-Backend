package com.DTeam.eshop.controllers;

import java.util.List;

import com.DTeam.eshop.entities.Address;
import com.DTeam.eshop.services.AddressService;
import com.DTeam.eshop.utilities.CustomErrorType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@CrossOrigin(origins="http://localhost:3000")
public class AddressController {

    @Autowired
    private AddressService addressService;

    // Retrieve all address
    @GetMapping("/addresses")
    public ResponseEntity<List<Address>> getAddresses() {
        List<Address> addresses = addressService.listAll();
        if(addresses.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Address>>(addresses, HttpStatus.OK);
    }

    //Retrieve single address
    @GetMapping("/addresses/{id}")
    public ResponseEntity<?> getAddress(@PathVariable("id")Long addressId){
        if(addressService.isAddressExist(addressId)){
            Address address = addressService.get(addressId);
            return new ResponseEntity<Address>(address, HttpStatus.OK);
        }
        return new ResponseEntity<>(new CustomErrorType("Address with id " + addressId + " not found."),HttpStatus.NOT_FOUND);
    }

    //Create a address
    @PostMapping("/addresses")
    public ResponseEntity<?> createAddress(@RequestBody Address address, UriComponentsBuilder ucBuilder){
        Long id = address.getAddressId();
        if(id != null){
            return new ResponseEntity<>(new CustomErrorType("Unable to create. A address with id " + id
            + " already exist."), HttpStatus.CONFLICT);
        }
        addressService.save(address);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api/addresses/{id}").buildAndExpand(address.getAddressId()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

    //Update a address
    @PutMapping("/addresses/{id}")
    public ResponseEntity<?> updateAddress(@PathVariable("id")Long addressId, @RequestBody Address address){
        if(addressService.isAddressExist(addressId)){
            Address currentAddress = addressService.get(addressId);
            currentAddress.setStreet(address.getStreet());
            currentAddress.setHouseNumber(address.getHouseNumber());
            currentAddress.setFlatNumber(address.getFlatNumber());
            currentAddress.setPostcode(address.getPostcode());
            currentAddress.setCity(address.getCity());
            addressService.save(currentAddress);

            return new ResponseEntity<Address>(currentAddress, HttpStatus.OK);
        }
        return new ResponseEntity<>(new CustomErrorType("Unable to update. Address with id " + addressId + " not found"),
        HttpStatus.NOT_FOUND);
    }

    //Delete a address
    @DeleteMapping("/addresses/{id}")
    public ResponseEntity<?> deleteAddress(@PathVariable("id")Long addressId){
        if(addressService.isAddressExist(addressId)){
            addressService.delete(addressId);
            return new ResponseEntity<Address>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(new CustomErrorType("Unable to delete. Address with id " + addressId + " not found."),
        HttpStatus.NOT_FOUND);
    }
}
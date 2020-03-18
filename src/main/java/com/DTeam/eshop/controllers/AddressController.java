package com.DTeam.eshop.controllers;

import java.util.List;

import com.DTeam.eshop.entities.Address;
import com.DTeam.eshop.services.AddressService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AddressController {

    @Autowired
    private AddressService addressService;

    @GetMapping("/address/list")
    public String getAll(Model model){
        List<Address> addressList = addressService.listAll();
        model.addAttribute("addressList", addressList);
        return "views/address/list";
    }

    @GetMapping("/address/add")
    public String save(Model model){
        Address address = new Address();
        model.addAttribute("address", address);
        return "views/address/add";
    }

    @PostMapping("/address/add")
    public String save(Address address){
        addressService.save(address);
        return "redirect:/address/list";
    }

    @GetMapping("/address/edit/{id}")
    public String edit(@PathVariable(name = "id")Long id, Model model){
        Address address = addressService.get(id);
        model.addAttribute("address", address);
        return "views/address/edit";
    }

    @PostMapping("/address/edit/{id}")
    public String edit(@PathVariable(name = "id")Long id, Address address){
        address.setAddressId(id);
        addressService.save(address);
        return "redirect:/address/list";
    }

    @GetMapping("/address/delete/{id}")
    public String delete(@PathVariable(name = "id")Long id){
        addressService.delete(id);
        return "redirect:/address/list";
    }
}
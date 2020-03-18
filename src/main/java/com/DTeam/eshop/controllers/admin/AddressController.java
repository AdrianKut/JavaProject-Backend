package com.DTeam.eshop.controllers.admin;

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

    @GetMapping("/admin/address/list")
    public String getAll(Model model){
        List<Address> addressList = addressService.listAll();
        model.addAttribute("addressList", addressList);
        return "views/admin/address/list";
    }

    @GetMapping("/admin/address/add")
    public String save(Model model){
        Address address = new Address();
        model.addAttribute("address", address);
        return "views/admin/address/add";
    }

    @PostMapping("/admin/address/add")
    public String save(Address address){
        addressService.save(address);
        return "redirect:/admin/address/list";
    }

    @GetMapping("/admin/address/edit/{id}")
    public String edit(@PathVariable(name = "id")Long id, Model model){
        Address address = addressService.get(id);
        model.addAttribute("address", address);
        return "views/admin/address/edit";
    }

    @PostMapping("/admin/address/edit/{id}")
    public String edit(@PathVariable(name = "id")Long id, Address address){
        address.setAddressId(id);
        addressService.save(address);
        return "redirect:/admin/address/list";
    }

    @GetMapping("/admin/address/delete/{id}")
    public String delete(@PathVariable(name = "id")Long id){
        addressService.delete(id);
        return "redirect:/admin/address/list";
    }
}
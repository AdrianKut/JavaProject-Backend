package com.DTeam.eshop.controllers.admin;

import java.util.List;

import javax.validation.Valid;

import com.DTeam.eshop.entities.Address;
import com.DTeam.eshop.services.AddressService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
/**
 * Klasa pozwalająca na obsługę adresów.
 * Zawiera metody do pobieranie danych,
 * dodawanie,edytowanie i usuwanie.
 * 
 * @author Mateusz Kozik
 */
@Controller
public class AddressController {

    @Autowired
    private AddressService addressService;

    /**
     * Metoda używana do wyświetlenie wszystkich adresów
     * zapisanych w bazie
     * @param model przechowywanie atrybutów modelu
     * @return zwracanie widok tabeli z listą adresów
     */
    @GetMapping("/admin/address/list")
    public String getAll(Model model){
        List<Address> addressList = addressService.listAll();
        model.addAttribute("addressList", addressList);
        return "views/admin/address/list";
    }

    /**
     * Metoda pobierająca adresy do formularza dodawania
     * @param model przechowywanie atrybutów modelu
     * @return zwracanie widok formularza dodania adresu
     */
    @GetMapping("/admin/address/add")
    public String save(Model model){
        Address address = new Address();
        model.addAttribute("address", address);
        return "views/admin/address/add";
    }

    /**
     * Metoda pozwalająca na zapisanie adresu w bazie
     * @param address pobranie obiektu adres
     * @param bindingResult walidacja błędów 
     * @return zwracanie widok tabeli z listą adresów
     */
    @PostMapping("/admin/address/add")
    public String save(@Valid Address address, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "views/admin/address/add";
        }
        addressService.save(address);
        return "redirect:/admin/address/list";
    }


    /**
     * Metoda pobierająca id adresu w celu
     * pobrania jego wartośći i wczytanie ich 
     * w pola formularza w celu edytowania.
     * @param id adresu
     */
    @GetMapping("/admin/address/edit/{id}")
    public String edit(@PathVariable(name = "id")Long id, Model model){
        Address address = addressService.get(id);
        model.addAttribute("address", address);
        return "views/admin/address/edit";
    }

    /**
     * Metoda pozwalająca na zapisanie edytowanego adresu
     * @param address pobranie obiektu adres
     * @param bindingResult walidacja błędów
     * @return zwracanie widok tabeli z listą adresów
     */
    @PostMapping("/admin/address/edit/{id}")
    public String edit(@PathVariable(name = "id")Long id, @Valid Address address, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "views/admin/address/edit";
        }

        address.setAddressId(id);
        addressService.save(address);
        return "redirect:/admin/address/list";
    }

    /**
     * Metoda pozwalająca na usunięcie adresu z bazy
     */
    @GetMapping("/admin/address/delete/{id}")
    public String delete(@PathVariable(name = "id")Long id){
        addressService.delete(id);
        return "redirect:/admin/address/list";
    }
}
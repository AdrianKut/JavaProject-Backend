package com.DTeam.eshop.controllers.employee;

import java.util.List;

import com.DTeam.eshop.entities.Complaint;
import com.DTeam.eshop.services.ComplaintService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * Klasa obsługująca Pracownik-Reklamacja
 * @author User
 */
@Controller
public class EmployeeComplaintController {

    @Autowired
    private ComplaintService complaintService;

    /**
     * Metoda wyswietla Liste Reklamacji
     * @param model przechowywanie atrybutów modelu
     * @return widok listy Reklamacji
     */
    @GetMapping("/employee/complaint-list")
    public String getAll(Model model){
        List<Complaint> complaintList = complaintService.getComplaintByStatus();
        model.addAttribute("complaintList",complaintList);
        return "views/employee/listComplaint";
    }

    /**
     * Metoda edycji Reklamacji
     * @param model przechowywanie atrybutów modelu
     * @param id przechowuje id Reklamacji
     * @return widok edycji formularza Reklamacji
     */
    @GetMapping("/employee/complaint-edit/{id}")
    public String edit(Model model,@PathVariable(name = "id") long id){
        Complaint complaint = complaintService.get(id);
        model.addAttribute("complaint", complaint);
        return "views/employee/editComplaint";
    }

    /**
     * Metoda edycji Reklamacji
     * @param id przechowuje id Reklamacji
     * @param complaint przechowuje Dane Reklamacji
     * @return widok listy Reklamacji
     */
    @PostMapping("/employee/complaint-edit/{id}")
    public String edit(@PathVariable(name = "id") long id,
    Complaint complaint){
        Complaint currnetComplaint = complaintService.get(id);
        currnetComplaint.setComplaintStatus(complaint.getComplaintStatus());
        complaintService.save(currnetComplaint);
        return "redirect:/employee/complaint-list";
    }



}
package com.DTeam.eshop.controllers;

import java.util.List;

import com.DTeam.eshop.entities.Complaint;
import com.DTeam.eshop.services.ComplaintService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ComplaintController {
    @Autowired
    private ComplaintService complaintService;

    @RequestMapping(value = "/complaint/list", method = RequestMethod.GET)
    public String getAll(Model model){
        List<Complaint> complaintList = complaintService.listAll();
        model.addAttribute("complaintList",complaintList);
        return "/views/complaint/list";
    }

    @RequestMapping(value = "/complaint/add", method = RequestMethod.GET)
    public String save(Model model) {
        Complaint complaint = new Complaint();
        model.addAttribute("complaint", complaint);
        return "/views/complaint/add";
    }

    @RequestMapping(value = "/complaint/add", method = RequestMethod.POST)
    public String save(Complaint complaint){
         
        complaintService.save(complaint);
        return "redirect:/complaint/list";
    }

    @RequestMapping(value = "/complaint/edit/{id}", method = RequestMethod.GET)
    public String edit(Model model,@PathVariable(name = "id") long id){
        
        Complaint complaint = complaintService.get(id);
        model.addAttribute("complaint", complaint);
        return "/views/complaint/edit";
    }
    
    @RequestMapping(value = "/complaint/edit/{id}", method = RequestMethod.POST)
    public String edit(@PathVariable(name = "id") long id, 
    Complaint complaint){ 
       
        complaint.setComplaintId(id);
        complaintService.save(complaint);
        return "redirect:/complaint/list";
    }

    @RequestMapping(value = "/complaint/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable(name = "id") long id){
        complaintService.delete(id);
        return "redirect:/complaint/list";
    }


}
package com.DTeam.eshop.services;

import java.util.List;

import com.DTeam.eshop.entities.Complaint;
import com.DTeam.eshop.entities.Customer;
import com.DTeam.eshop.repositories.ComplaintRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComplaintService {

    @Autowired private ComplaintRepository complaintRepository;

    public List<Complaint> listAll(){
        return complaintRepository.findAll();
    }

    public Complaint save(Complaint complaint){
       return complaintRepository.save(complaint);
    }

    public Complaint get(Long id){
        return complaintRepository.findById(id).get();
    }

    public Complaint delete(Long id){
        Complaint complaint = get(id);
        complaintRepository.deleteById(id);
       return complaint;
    }

    public Boolean isComplaintExist(Long id){
        return complaintRepository.existsById(id);
    }

    public List<Complaint> getByCustomer(Customer customer){
        return complaintRepository.findByOrderCustomer(customer);
    }
}
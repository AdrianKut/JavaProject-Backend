package com.DTeam.eshop.services;

import java.util.List;

import com.DTeam.eshop.entities.Complaint;
import com.DTeam.eshop.repositories.ComplaintRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComplaintService {

    @Autowired private ComplaintRepository complaintRepository;
    
    public List<Complaint> listAll(){
        return complaintRepository.findAll();
    }

    public void save(Complaint complaint){
        complaintRepository.save(complaint);
    }

    public Complaint get(Long id){
        return complaintRepository.findById(id).get();
    }

    public void delete(Long id){
        complaintRepository.deleteById(id);
    }
}
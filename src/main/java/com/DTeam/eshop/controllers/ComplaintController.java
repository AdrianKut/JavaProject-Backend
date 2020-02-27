package com.DTeam.eshop.controllers;

import java.util.List;

import com.DTeam.eshop.entities.Complaint;
import com.DTeam.eshop.services.ComplaintService;
import com.DTeam.eshop.utilities.CustomErrorType;
import org.springframework.http.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
public class ComplaintController {

    @Autowired private ComplaintService complaintService;

    //Retrieve all complaints
    @GetMapping(value="/complaints")
    public ResponseEntity<List<Complaint>> getComplaints() {
        List<Complaint> complaints = complaintService.listAll();
        if(complaints.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Complaint>>(complaints, HttpStatus.OK);
    }

    //Retrieve single complaint
    @GetMapping(value = "/complaint/{id}")
    public ResponseEntity<?> getComplaint(@PathVariable("id")Long complaintId){
        if(complaintService.isComplaintExist(complaintId)){
            Complaint complaint = complaintService.get(complaintId);
            return new ResponseEntity<Complaint>(complaint, HttpStatus.OK);
        }
        return new ResponseEntity<>(new CustomErrorType("Complaint with id " + complaintId + " not found"), HttpStatus.NOT_FOUND);
    }

    //Create a complaint
    @PostMapping(value="/complaint")
    public ResponseEntity<?> createProduct(@RequestBody Complaint complaint, UriComponentsBuilder ucBuilder) {
        Long id = complaint.getComplaintId();
        if(complaintService.isComplaintExist(id)){
            return new ResponseEntity<>(new CustomErrorType("Unable to create. A complaint with id " + id + 
            " already exist."), HttpStatus.CONFLICT);
        }
        complaintService.save(complaint);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api/product/{id}").buildAndExpand(complaint.getComplaintId()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

       //Update a complaint
       @PutMapping("/complaint/{id}")
       public ResponseEntity<?> updateComplaint(@PathVariable("id")Long complaintId, @RequestBody Complaint complaint){
           if(complaintService.isComplaintExist(complaintId)){
            Complaint currentComplaint = complaintService.get(complaintId);
            currentComplaint.setNotificationDate(complaint.getNotificationDate());
            currentComplaint.setDescription(complaint.getDescription());
            currentComplaint.setComplaintStatus(complaint.getComplaintStatus());
            complaintService.save(currentComplaint);
   
               return new ResponseEntity<Complaint>(currentComplaint, HttpStatus.OK);
           }
   
           return new ResponseEntity<>(new CustomErrorType("Unable to update. Complaint with id " + complaintId + " not found."),
           HttpStatus.NOT_FOUND);
       }

       //Delete a complaint
    @DeleteMapping("/complaint/{id}")
    public ResponseEntity<?> deleteComplaint(@PathVariable("id")Long complaintId){
        if(complaintService.isComplaintExist(complaintId)){
            complaintService.delete(complaintId);
            return new ResponseEntity<Complaint>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(new CustomErrorType("Unable to delete. Complaint with id " + complaintId + " not found"),
        HttpStatus.NOT_FOUND);
    }
}
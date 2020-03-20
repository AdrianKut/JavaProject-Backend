package com.DTeam.eshop.repositories;

import java.util.List;

import com.DTeam.eshop.entities.Complaint;
import com.DTeam.eshop.entities.Customer;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ComplaintRepository extends JpaRepository<Complaint, Long> {
    List<Complaint> findByOrderCustomer(Customer customer);
    List<Complaint> findByComplaintStatusOrComplaintStatus(String complaintStatusOne,String complaintStatusTwo);
}
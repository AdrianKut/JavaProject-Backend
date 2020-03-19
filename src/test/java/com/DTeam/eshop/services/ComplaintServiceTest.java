package com.DTeam.eshop.services;


import com.DTeam.eshop.entities.Complaint;
import com.DTeam.eshop.entities.Address;



import java.time.LocalDateTime;

import java.util.List;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

import org.hamcrest.Matchers;
import org.mockito.Mockito;


import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.junit.Assert.*;

public class ComplaintServiceTest {

    @Test
    public void testListAll() {

        //given
        ComplaintService complaintService = mock(ComplaintService.class);

        //when
        when(complaintService.listAll()).thenReturn(prepareMocData());

        //then
        assertEquals(complaintService.listAll().get(0).getComplaintId().longValue(), 6L);
        assertThat(complaintService.listAll(), Matchers.hasSize(3));

    }

    private List<Complaint> prepareMocData() {

        List<Complaint> complaints = new ArrayList<>();
        complaints.add(new Complaint(6L, LocalDateTime.parse("2012-02-22T02:06:58.147"), "Cos nie dziala", "PRZYJETO"));
        complaints.add(new Complaint(8L, LocalDateTime.parse("2012-02-22T02:06:58.147"), "Caly czas tluklo", "ODRZUCONO"));
        complaints.add(new Complaint(10L, LocalDateTime.parse("2012-02-22T02:06:58.147"), "Cos nie dziala", "W DRODZE"));

        return complaints;
    }

    @Test
    public void save() {

        //given
        ComplaintService complaintService = mock(ComplaintService.class);

        //when
        when(complaintService.save(Mockito.any(Complaint.class))).thenReturn(new Complaint());
        Complaint complaint = complaintService.save(new Complaint());

        //then
        assertEquals(complaint.getDescription(), null);
        assertEquals(complaint.getComplaintStatus(), null);
    }
    
    
//    @Test
//    public void testGet() {
//    }
//
//    @Test
//    public void testDelete() {
//    }
//
//    @Test
//    public void testIsComplaintExist() {
//    }

}

package com.DTeam.eshop.services;

import com.DTeam.eshop.entities.Complaint;
import java.time.LocalDateTime;

import java.util.List;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

import org.hamcrest.Matchers;
import org.mockito.Mockito;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
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
        complaints.add(new Complaint(6L, LocalDateTime.parse("2020-05-30T02:06:58.198"), "Cos nie dziala", "PRZYJETO"));
        complaints.add(new Complaint(8L, LocalDateTime.parse("2019-08-15T02:08:54.115"), "Caly czas tluklo", "ODRZUCONO"));
        complaints.add(new Complaint(10L, LocalDateTime.parse("2018-12-10T02:01:58.147"), "Cos nie dziala", "W DRODZE"));

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

    @Test
    public void get() {

        Long id = 14L;

        //given
        ComplaintService complaintService = mock(ComplaintService.class);

        //when
        when(complaintService.get(id)).thenReturn(new Complaint());

        //then
        assertEquals(complaintService.get(id).getOrder(), null);
        assertEquals(complaintService.get(id).getDescription(), null);
    }

    @Test
    public void delete() {

        Long id = 156L;
        final Complaint customer = new Complaint(id, LocalDateTime.parse("2020-02-22T02:06:58.147"), "Cos nie dziala", "PRZYJETO");

        //given
        ComplaintService complaintService = mock(ComplaintService.class);

        //when
        complaintService.delete(customer.getComplaintId());

        //then
        verify(complaintService, times(1)).delete(id);
    }

    @Test
    public void isComplaintExist() {

        Long id = 999L;

        //given
        ComplaintService complaintService = mock(ComplaintService.class);

        //when
        when(complaintService.isComplaintExist(id)).thenReturn(true);

        //then
        final boolean result = complaintService.isComplaintExist(id);
        assertEquals(result, true);

    }
}

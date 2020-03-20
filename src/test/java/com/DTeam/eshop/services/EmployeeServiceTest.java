package com.DTeam.eshop.services;

import com.DTeam.eshop.entities.Employee;
import java.time.LocalDate;

import java.util.List;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

import org.hamcrest.Matchers;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.junit.Assert.*;

public class EmployeeServiceTest {

    public EmployeeServiceTest() {
    }

    @Test
    public void testListAll() {

        //given
        EmployeeService employeeService = mock(EmployeeService.class);

        //when
        when(employeeService.listAll()).thenReturn(prepareMocData());

        //then
        assertEquals(employeeService.listAll().get(0).getAddress(), null);
        assertEquals(employeeService.listAll().get(2).getSurname(), "Surname");
        assertThat(employeeService.listAll(), Matchers.hasSize(3));

    }

    private List<Employee> prepareMocData() {
        List<Employee> employees = new ArrayList<>();

        employees.add(new Employee());
        employees.add(new Employee());
        employees.add(new Employee(320L, "Name", "Surname", LocalDate.now(), 12340.50, "BOSS"));
        return employees;
    }

//    @Test
//    public void testSave() {
//    }
//
//    @Test
//    public void testGet() {
//    }
//
//    @Test
//    public void testDelete() {
//    }
//
//    @Test
//    public void testIsEmployeeExist() {
//    }
//
//    @Test
//    public void testGetByEmail() {
//    }
}

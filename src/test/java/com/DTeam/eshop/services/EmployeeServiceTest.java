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

    //given
    EmployeeService employeeService = mock(EmployeeService.class);

    @Test
    public void testListAll() {

        //when
        when(employeeService.listAll()).thenReturn(prepareMocData());

        //then
        assertEquals(employeeService.listAll().get(0).getAddress(), null);
        assertEquals(employeeService.listAll().get(2).getSurname(), "Surname");
        assertEquals(employeeService.listAll().get(2).getBasePay().toString(), "12340.5");
        assertThat(employeeService.listAll(), Matchers.hasSize(3));

    }

    private List<Employee> prepareMocData() {

        List<Employee> employees = new ArrayList<>();

        employees.add(new Employee());
        employees.add(new Employee());
        employees.add(new Employee(320L, "Name", "Surname", LocalDate.now(), 12340.50, "BOSS"));
        return employees;
    }

    @Test
    public void testSave() {

        //when
        when(employeeService.save(Mockito.any(Employee.class))).thenReturn(new Employee(156L, "John", "Cena", LocalDate.now(), 456.50, "Test"));
        Employee employee = employeeService.save(new Employee());

        //then
        assertEquals(employee.getEmployeeId().toString(), "" + 156L);
        assertEquals(employee.getBasePay().toString(), "" + 456.50);
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
//    public void testIsEmployeeExist() {
//    }
//
//    @Test
//    public void testGetByEmail() {
//    }
}

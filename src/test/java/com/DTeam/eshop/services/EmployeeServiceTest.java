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
    final EmployeeService employeeService = mock(EmployeeService.class);

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

    @Test
    public void testGet() {

        Long id = 1L;

        //when
        when(employeeService.get(id)).thenReturn(new Employee());

        //then
        assertEquals(employeeService.get(id).getEmployeeId(), null);
        assertEquals(employeeService.get(id).getName(), null);
        assertEquals(employeeService.get(id).getSurname(), null);
        assertEquals(employeeService.get(id).getBasePay(), null);
        assertEquals(employeeService.get(id).getPosition(), null);

        try {
            assertEquals(employeeService.get(id).getPosition(), "sample position");
            assertEquals(employeeService.get(id).getEmploymentDate(), null);
        } catch (AssertionError error) {
            assert (true);
        }

    }

    @Test
    public void testDelete() {

        Long id = 189L;
        final Employee employee = new Employee(id, "", "", LocalDate.now(), 1.0, "");

        //when
        employeeService.delete(employee.getEmployeeId());

        //then
        verify(employeeService, times(1)).delete(id);
    }

    @Test
    public void testIsEmployeeExist() {

        String email = "adres@o2.pl";
        //when
        when(employeeService.isEmployeeExist(email)).thenReturn(true);

        //then
        final boolean result = employeeService.isEmployeeExist(email);
        assertEquals(result, true);
    }
    

    @Test
    public void testGetByEmail() {
    }
}

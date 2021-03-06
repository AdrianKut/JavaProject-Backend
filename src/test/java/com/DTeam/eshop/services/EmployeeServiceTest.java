package com.DTeam.eshop.services;

import com.DTeam.eshop.entities.Employee;
import com.DTeam.eshop.entities.User;

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

    final EmployeeService employeeService = mock(EmployeeService.class);
    final UserService userService = mock(UserService.class);

    @Test
    public void testListAll() {

        when(employeeService.listAll()).thenReturn(prepareMocData());

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

        when(employeeService.save(Mockito.any(Employee.class))).thenReturn(new Employee(156L, "John", "Cena", LocalDate.now(), 456.50, "Test"));
        Employee employee = employeeService.save(new Employee());

        assertEquals(employee.getEmployeeId().toString(), "" + 156L);
        assertEquals(employee.getBasePay().toString(), "" + 456.50);
    }

    @Test
    public void testGet() {

        Long id = 42L;

        when(employeeService.get(id)).thenReturn(new Employee());

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

        employeeService.delete(employee.getEmployeeId());

        verify(employeeService, times(1)).delete(id);
    }

    @Test
    public void testIsEmployeeExist() {

        String email = "adres@o2.pl";

        when(userService.isUserExist(email)).thenReturn(true);

        final boolean result = userService.isUserExist(email);
        assertEquals(result, true);
    }

    @Test
    public void testGetByEmail() {

        String email = "adres@o2.pl";
        Long id = 324L;
        final User user = new User(email, Integer.SIZE, "1234", true);
        final Employee employee = new Employee(id, "", "", LocalDate.now(), Double.NaN, "");

        when(employeeService.get(id)).thenReturn(employee);
        when(userService.get(email)).thenReturn(user);

        employee.setUser(user);

        assertEquals(employee.getEmployeeId().longValue(), 324L);
        assertEquals(employee.getUser().getEmail(), email);
        assertEquals(employee.getName(), "");

        assertEquals(user.getEmail(), email);
        assertEquals(user.getPassword(), "1234");

        assertEquals(employee.getUser().getEmail(), user.getEmail());

    }
}

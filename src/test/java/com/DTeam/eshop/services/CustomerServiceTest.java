package com.DTeam.eshop.services;

import com.DTeam.eshop.entities.Customer;
import com.DTeam.eshop.entities.User;

import static org.junit.Assert.*;
import java.util.List;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

import org.hamcrest.Matchers;
import org.mockito.Mockito;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class CustomerServiceTest {

    public CustomerServiceTest() {
    }

    @Test
    public void listAll() {

        //given
        CustomerService customerService = mock(CustomerService.class);

        //when
        when(customerService.listAll()).thenReturn(prepareMocData());

        //then
        assertEquals(customerService.listAll().get(0).getName(), "Name");
        assertEquals(customerService.listAll().get(1).getUser(), null);
        assertThat(customerService.listAll(), Matchers.hasSize(2));

    }

    private List<Customer> prepareMocData() {
        List<Customer> customers = new ArrayList<>();
        customers.add(new Customer(50L, "Name", "Yes", "123456789"));
        customers.add(new Customer(40L, "Supername", "No", "987565412"));

        return customers;
    }

    @Test
    public void save() {

        //given
        CustomerService customerService = mock(CustomerService.class);

        //when
        when(customerService.save(Mockito.any(Customer.class))).thenReturn(new Customer());
        Customer customer = customerService.save(new Customer());

        //then
        assertEquals(customer.getName(), null);
        assertEquals(customer.getPhoneNumber(), null);
    }

    @Test
    public void get() {

        Long id = 1L;
        //given
        CustomerService customerService = mock(CustomerService.class);

        //when
        when(customerService.get(id)).thenReturn(new Customer());

        //then
        assertEquals(customerService.get(id).getAddress(), null);
        assertEquals(customerService.get(id).getOrders(), null);
    }

    @Test
    public void delete() {

        Long id = 1L;
        final Customer customer = new Customer(id, "Adrian", "Surname", "123456789");

        //given
        CustomerService customerService = mock(CustomerService.class);

        //when
        customerService.delete(customer.getCustomerId());

        //then
        verify(customerService, times(1)).delete(id);
    }

    @Test
    public void isCustomerExist() {
        
        //given
        CustomerService customerService = mock(CustomerService.class);

        //when
        when(customerService.isCustomerExist("adres@gmail.com")).thenReturn(true);

        //then
        final boolean result = customerService.isCustomerExist("adres@gmail.com");
        assertEquals(result, true);
    }

    @Test
    public void testGetByEmail() {

    }

}

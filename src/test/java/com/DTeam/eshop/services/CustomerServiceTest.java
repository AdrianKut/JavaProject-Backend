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
    public void testListAll() {

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
    public void testSave() {

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
    public void testGet() {

    }

    @Test
    public void testDelete() {

    }

    @Test
    public void testIsCustomerExist() {
    }

    @Test
    public void testGetByEmail() {

    }

}

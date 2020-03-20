package com.DTeam.eshop.services;

import com.DTeam.eshop.entities.Order;
import java.time.LocalDateTime;

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

public class OrderServiceTest {

    final OrderService orderService = mock(OrderService.class);

    @Test
    public void testListAll() {

        String localDateTime = LocalDateTime.now().toString();
        //when
        when(orderService.listAll()).thenReturn(prepareMocData(LocalDateTime.parse(localDateTime)));

        //then
        assertEquals(orderService.listAll().get(0).getOrderId(), null);
        assertEquals(orderService.listAll().get(2).getOrderId().longValue(), 32L);
        assertEquals(orderService.listAll().get(2).getPurchaseDate().toString(),localDateTime);
        assertThat(orderService.listAll(), Matchers.hasSize(3));
    }

    private List<Order> prepareMocData(LocalDateTime localDateTime) {

        
        List<Order> orders = new ArrayList<>();

        orders.add(new Order());
        orders.add(new Order());
        orders.add(new Order(32L, localDateTime));
        return orders;
    }

    @Test
    public void testSave() {
    }

    @Test
    public void testSaveEdit() {
    }

    @Test
    public void testGet() {
    }

    @Test
    public void testDelete() {
    }

    @Test
    public void testIsOrderExist() {
    }

    @Test
    public void testGetByCustomer() {
    }

    @Test
    public void testGetOrderByStatus() {
    }

}

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
        assertEquals(orderService.listAll().get(2).getPurchaseDate().toString(), localDateTime);
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

//        //when
//        when(orderService.save(Mockito.any(Order.class)).thenReturn(new Order()));
//        Order order = orderService.save(new Order());
//
//        //then
//        assertEquals(order.getEmployeeId().toString(), "" + 156L);
    }

    @Test
    public void testSaveEdit() {
    }

    @Test
    public void testGet() {
        
        Long id = 894L;

        //when
        when(orderService.get(id)).thenReturn(new Order());

        //then
        assertEquals(orderService.get(id).getOrderId(), null);
        assertEquals(orderService.get(id).getPurchaseDate().toString(), "");

        try {
            assertEquals(orderService.get(id).getCustomer(), "sample position");
            assertEquals(orderService.get(id).getShipmentDate(), null);
        } catch (AssertionError error) {
            assert (true);
        }
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

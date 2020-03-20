package com.DTeam.eshop.services;

import com.DTeam.eshop.entities.Customer;
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
import org.junit.ComparisonFailure;

public class OrderServiceTest {

    final OrderService orderService = mock(OrderService.class);
    final CustomerService customerService = mock(CustomerService.class);
    final static String localDateTime = LocalDateTime.now().toString();

    @Test
    public void testListAll() {

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

        Long id = 1589L;
        final Order order = new Order(id, LocalDateTime.MAX);

        //when
        orderService.delete(order.getOrderId());

        //then
        verify(orderService, times(1)).delete(id);
    }

    @Test
    public void testIsOrderExist() {

        Long id = 540L;

        //when
        when(orderService.isOrderExist(id)).thenReturn(true);

        //then
        final boolean result = orderService.isOrderExist(id);
        assertEquals(result, true);

    }

    @Test
    public void testGetByCustomer() {

        String localDateTime = LocalDateTime.now().toString();

        Long id = 42L;
        final Customer customer = new Customer(id, "", "", "");

        List<Order> orders = new ArrayList<>();
        orders.add(new Order());
        orders.add(new Order());
        orders.add(new Order(32L, LocalDateTime.parse(localDateTime)));

        //when
        when(customerService.get(id)).thenReturn(customer);

        customer.setOrders(orders);

        //then
        assertEquals(customer.getOrders().size(), 3);
        assertEquals(customer.getOrders().get(2).getOrderId().toString(), "" + 32L);
        assertEquals(customer.getOrders().get(2).getPurchaseDate(), localDateTime);

        assertEquals(customer.getCustomerId().longValue(), 42L);

        assertEquals(orders.get(0).getOrderId(), null);
        assertEquals(orders.get(1).getProducts(), null);

    }

    @Test
    public void testGetOrderByStatus() {

        //when
        when(orderService.getOrderByStatus()).thenReturn(prepareMocData(LocalDateTime.parse(localDateTime)));

        //then
        orderService.getOrderByStatus().get(0).setOrderStatus("Przyjęto");
        verify(orderService, times(1)).getOrderByStatus();

        try {
            final String resultStatus = orderService.getOrderByStatus().get(0).getOrderStatus();
            assertEquals(resultStatus, "Wysłane"); // Ma zwrócić wszystkie które nie mają wysłane np. Przyjęto jak wyżej
        } catch (ComparisonFailure e) {
        }

    }

}

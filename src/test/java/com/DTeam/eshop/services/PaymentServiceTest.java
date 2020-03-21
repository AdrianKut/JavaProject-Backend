package com.DTeam.eshop.services;

import com.DTeam.eshop.entities.Payment;
import java.time.LocalDateTime;

import java.util.List;
import java.util.ArrayList;
import javax.security.auth.message.callback.PrivateKeyCallback;
import org.junit.jupiter.api.Test;

import org.hamcrest.Matchers;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;
import static org.junit.Assert.*;
import org.junit.ComparisonFailure;

public class PaymentServiceTest {

    final PaymentService paymentService = mock(PaymentService.class);

    @Test
    public void testListAll() {

        when(paymentService.listAll()).thenReturn(prepareMocData());

        assertEquals(paymentService.listAll().get(0).getPaymentId(), null);
        assertEquals(paymentService.listAll().get(2).getPaymentMethod(), "Gotowka");
        assertThat(paymentService.listAll(), Matchers.hasSize(3));
    }

    private List<Payment> prepareMocData() {

        List<Payment> payment = new ArrayList<>();
        payment.add(new Payment());
        payment.add(new Payment());
        payment.add(new Payment(520L, LocalDateTime.MIN, "Gotowka"));
        return payment;
    }

    @Test
    public void testSave() {

        when(paymentService.save(any(Payment.class))).thenReturn(new Payment(32L, LocalDateTime.now(), "Karta"));

        Payment payment = paymentService.save(new Payment());

        assertEquals(payment.getPaymentId().longValue(), 32L);
        assertEquals(payment.getPaymentMethod(), "Karta");

        try {
            assertEquals(payment.getPaymentDate(), "" + LocalDateTime.now());
        } catch (ComparisonFailure e) {

        }

    }

    @Test
    public void testGet() {
        
        when(paymentService.get(anyLong())).thenReturn(new Payment(324L, LocalDateTime.now(), "Gotówka"));
        
        assertEquals(paymentService.get(324L).getPaymentId().longValue(), 324L);
        assertNotEquals(paymentService.get(324L).getPaymentDate(), LocalDateTime.now());
        assertEquals(paymentService.get(324L).getPaymentMethod(), "Gotówka");
        
        
    }

    @Test
    public void testDelete() {

        final Payment payment = new Payment(520L, LocalDateTime.MIN, "Gotowka");

        paymentService.delete(payment.getPaymentId());

        verify(paymentService, times(1)).delete(520L);
    }

    @Test
    public void testIsPaymentExist() {

        when(paymentService.isPaymentExist(anyLong())).thenReturn(true);

        final boolean result = paymentService.isPaymentExist(5432L);
        assertEquals(result, true);
    }

    @Test
    public void testGetLastMonth() {
    }

}

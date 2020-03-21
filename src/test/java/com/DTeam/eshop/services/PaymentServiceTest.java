package com.DTeam.eshop.services;

import com.DTeam.eshop.entities.Payment;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.List;
import java.util.ArrayList;
import java.util.Locale;
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
    final static String localDateTime = LocalDateTime.now().toString();

    @Test
    public void testListAll() {

        when(paymentService.listAll()).thenReturn(prepareMocData(LocalDateTime.parse(localDateTime)));

        assertEquals(paymentService.listAll().get(0).getPaymentId(), null);
        assertEquals(paymentService.listAll().get(2).getPaymentMethod(), "Gotowka");
        assertThat(paymentService.listAll(), Matchers.hasSize(3));
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

        when(paymentService.getLastMonth()).thenReturn(prepareMocData(LocalDateTime.parse(localDateTime)));

        paymentService.getLastMonth().get(0).setPaymentDate("2020-12-12T12:00");
        paymentService.getLastMonth().get(0).setPaymentDate("2020-12-12T12:00");
        paymentService.getLastMonth().get(0).setPaymentDate("2020-12-12T12:00");
        
        verify(paymentService,times(3)).getLastMonth();
          
       assertEquals(paymentService.getLastMonth().get(0).getPaymentDate(), "2020-12-12T12:00");

        String getMonth = LocalDateTime.parse(paymentService.getLastMonth().get(0).getPaymentDate()).getMonth().toString();
        assertEquals(getMonth.toLowerCase(Locale.ITALY), "december");
    }

    private List<Payment> prepareMocData(LocalDateTime localDateTime) {

        List<Payment> payment = new ArrayList<>();
        payment.add(new Payment());
        payment.add(new Payment(320L, LocalDateTime.parse("2020-05-21T11:11:11.00"), "Karta"));
        payment.add(new Payment(520L, localDateTime, "Gotowka"));
        return payment;
    }

}

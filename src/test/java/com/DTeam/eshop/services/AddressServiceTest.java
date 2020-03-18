package com.DTeam.eshop.services;

import com.DTeam.eshop.entities.Address;

import java.util.List;
import java.util.ArrayList;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import org.hamcrest.Matchers;
import org.mockito.Mockito;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AddressServiceTest {

    @Test
    public void listAll() {
        //given
        AddressService addressService = mock(AddressService.class);

        //when
        when(addressService.listAll()).thenReturn(prepareMocData());

        //then
        Assert.assertEquals(addressService.listAll().get(0).getCity(), "Rzeszow");
        Assert.assertThat(addressService.listAll(), Matchers.hasSize(2));

    }

    private List<Address> prepareMocData() {
        List<Address> addresses = new ArrayList<>();
        addresses.add(new Address(1L, "Rzeszow", 110, "38-111"));
        addresses.add(new Address(2L, "Strzyzow", 380, "41-350"));

        return addresses;
    }

    @Test
    public void save() {
        //given

        AddressService addressService = mock(AddressService.class);

        //when
        when(addressService.save(Mockito.any(Address.class))).thenReturn(new Address("Miasto", 32, "31-523"));
        Address address = addressService.save(new Address());

        //then
        Assert.assertEquals(address.getCity(), "Miasto");
        Assert.assertEquals(address.getHouseNumber().toString(), "" + 32);
        Assert.assertEquals(address.getPostcode(), "31-523");

    }

    @Test
    public void get() {
        //given
        AddressService addressService = mock(AddressService.class);

        //when
        when(addressService.get(Mockito.anyLong())).thenReturn(new Address(1L, "Miasto", 997, "36-156"));

        //then
        Address address = addressService.get(1L);
        Assert.assertEquals(address.getAddressId().longValue(), 1L);

    }
    
      @Test
    public void delete() {
        //given
        AddressService addressService = mock(AddressService.class);

        //when
        when(addressService.get(Mockito.anyLong())).thenReturn(new Address(1L, "Miasto", 997, "36-156"));

        //then
        Address address = addressService.delete(1L);
        Assert.assertThat(addressService.listAll(), Matchers.hasSize(0));
    }
    
    
}

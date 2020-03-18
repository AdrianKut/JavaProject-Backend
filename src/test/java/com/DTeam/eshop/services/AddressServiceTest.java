package com.DTeam.eshop.services;

import com.DTeam.eshop.entities.Address;


import java.util.List;
import java.util.ArrayList;
import org.junit.Assert;
import org.junit.jupiter.api.Test;


import org.hamcrest.Matchers;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AddressServiceTest {

     
    @Test
    public void get(){
          
          //given
          AddressService addressService = mock(AddressService.class);
          //when
          when(addressService.listAll()).thenReturn(prepareMocData());   
          //then
          Assert.assertThat(addressService.listAll(), Matchers.hasSize(2));

    }
    
     private List<Address> prepareMocData() {
        List<Address> addresses = new ArrayList<>();
        addresses.add(new Address("Dobrzechow",110));
        addresses.add(new Address("Strzyzow",380));
        
         return addresses;
    }
        
//    @Test
//    public void testSave() {
//    }
//
//
//    @Test
//    public void testDelete() {
//    }
//
   

}

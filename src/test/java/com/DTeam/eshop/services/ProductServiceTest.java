package com.DTeam.eshop.services;

import com.DTeam.eshop.entities.Product;
import com.DTeam.eshop.entities.User;

import java.util.List;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

import org.hamcrest.Matchers;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;
import static org.junit.Assert.*;

public class ProductServiceTest {

    final ProductService productService = mock(ProductService.class);

    @Test
    public void testListAll() {

        when(productService.listAll()).thenReturn(prepareMocData());

        assertEquals(productService.listAll().get(0).getOrders(), null);
        assertEquals(productService.listAll().get(2).getDescription(), "najlepsza ze wszystkich");
        assertThat(productService.listAll(), Matchers.hasSize(3));

        verify(productService, times(3)).listAll();

    }

    private List<Product> prepareMocData() {

        List<Product> products = new ArrayList<>();

        products.add(new Product());
        products.add(new Product());
        products.add(new Product(32L, "Klawiatura", "najlepsza ze wszystkich", 999.130, 32, "brak"));

        return products;
    }

    @Test
    public void testSave() {

        when(productService.save(any(Product.class))).thenReturn(new Product());

        Product product = productService.save(new Product());
        product = productService.save(new Product());

        verify(productService, times(2)).save(product);

        assertNotEquals(product.getPrice(), 234234.200);
        assertEquals(product.getProductId(), null);
    }

    @Test
    public void testGet() {

        when(productService.get(anyLong())).thenReturn(new Product(423L, "Monitor", "32'", 2343252.320, 1, "brak"));

        Product product = productService.get(423L);

        verify(productService, times(1)).get(any());

        assertEquals(product.getProductId().longValue(), 423L);
        assertEquals(product.getName(), "Monitor");
        assertEquals(product.getDescription(), "32'");
        assertEquals(product.getPrice().toString(), "" + 2343252.320);
        assertEquals(product.getPhoto(), "brak");

        assertNotEquals(productService.get(234L).getName(), null);

        verify(productService, times(2)).get(any());

    }

//
//    @Test
//    public void testDelete() {
//    }
//
//    @Test
//    public void testIsProductExist() {
//    }
//
//    @Test
//    public void testGetByOrderId() {
//    }
//
//    @Test
//    public void testGetByNameOrCategory() {
//    }
//
//    @Test
//    public void testGetByCategory() {
//    }
//
//    @Test
//    public void testgetProductsEnding() {
//
//    }
}

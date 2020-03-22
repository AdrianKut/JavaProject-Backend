package com.DTeam.eshop.services;

import com.DTeam.eshop.entities.Product;
import com.DTeam.eshop.entities.Order;
import java.time.LocalDateTime;
import java.time.Month;

import java.util.List;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

import org.hamcrest.Matchers;
import org.hibernate.type.AnyType;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;
import static org.junit.Assert.*;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.domain.PageRequest;

public class ProductServiceTest {

    final ProductService productService = mock(ProductService.class);
    final OrderService orderService = mock(OrderService.class);

    @Test
    public void testListAll() {

        when(productService.listAll()).thenReturn(prepareMocData());

        assertEquals(productService.listAll().get(0).getOrders(), null);
        assertEquals(productService.listAll().get(2).getDescription(), "najlepsza ze wszystkich");
        assertEquals(productService.listAll().get(2).getCategory(), "Podzespoły komputerowe");
        assertThat(productService.listAll(), Matchers.hasSize(3));

        verify(productService, times(4)).listAll();

    }

    private List<Product> prepareMocData() {

        List<Product> products = new ArrayList<>();

        products.add(new Product());
        products.add(new Product());
        products.add(new Product(32L, "Klawiatura", "najlepsza ze wszystkich", 999.130, 32, "brak", "Podzespoły komputerowe"));

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

        when(productService.get(anyLong())).thenReturn(new Product(423L, "Monitor", "32'", 2343252.320, 1, "brak", "Monitory i akcesoria"));

        Product product = productService.get(423L);

        verify(productService, times(1)).get(any());

        assertEquals(product.getProductId().longValue(), 423L);
        assertEquals(product.getName(), "Monitor");
        assertEquals(product.getDescription(), "32'");
        assertEquals(product.getPrice().toString(), "" + 2343252.320);
        assertEquals(product.getPhoto(), "brak");
        assertEquals(product.getCategory(), "Monitory i akcesoria");

        assertNotEquals(productService.get(234L).getName(), null);

        verify(productService, times(2)).get(any());

    }

    @Test
    public void testDelete() {

        when(productService.delete(anyLong())).thenReturn(new Product());

        productService.delete(new Product().getProductId());

        verify(productService, times(1)).delete(any());

    }

    @Test
    public void testIsProductExist() {

        when(productService.isProductExist(any())).thenReturn(true);

        final boolean result = productService.isProductExist(any());
        assertEquals(result, true);

        verify(productService, times(1)).isProductExist(any());
    }

    @Test
    public void testGetByOrderId() {

        List<Product> products = new ArrayList<>();

        products.add(new Product());
        products.add(new Product());
        products.add(new Product(10L, "Myszka", "Optyczna z podświetleniem RGB", 651.230, 32, "brak", "Sprzęt dla gracza"));

        Order order = new Order(15L, LocalDateTime.of(2020, Month.MARCH, 22, 21, 40));

        when(productService.getByOrderId(anyLong())).thenReturn(products);

        order.setProducts(products);

        assertEquals(productService.getByOrderId(10L).get(2).getName().toString(), "Myszka");

        assertEquals(order.getProducts().get(2).getPhoto(), "brak");
        assertEquals(order.getProducts().get(2).getCategory(), "Sprzęt dla gracza");
        assertEquals(order.getProducts().get(0).getPrice(), null);

        assertEquals(products.get(2).getPrice().toString(), "" + 651.230);

        verify(productService, times(1)).getByOrderId(anyLong());
    }

    @Test
    public void testGetByNameOrCategory() {

        List<Product> products = new ArrayList<>();

        products.add(new Product());
        products.add(new Product());
        products.add(new Product(189L, "Podkładka", "20x20", 59.99, 10, "brak", "Sprzęt dla graczy"));

        when(productService.getByNameOrCategory(anyString())).thenReturn(products);

        assertEquals(products.get(2).getName(), "Podkładka");
        assertEquals(products.get(2).getCategory(), "Sprzęt dla graczy");

        assertEquals(productService.getByNameOrCategory("Podkładka").get(2).getName(), "Podkładka");
        assertEquals(productService.getByNameOrCategory("Podkładka").get(2).getCategory(), "Sprzęt dla graczy");

        assertEquals(productService.getByNameOrCategory("").get(0).getCategory(), null);
        assertEquals(productService.getByNameOrCategory("").get(1).getCategory(), null);

        productService.getByNameOrCategory("Podzespoły komputerowe");
        productService.getByNameOrCategory("Monitor");

        verify(productService, times(6)).getByNameOrCategory(any());
    }

    @Test
    public void testGetByCategory() {

        Page<Product> products = mock(Page.class);
        products.and(new Product());
        products.and(new Product(10L, "Myszka", "Optyczna z podświetleniem RGB", 651.230, 2, "brak", "Podzespoły komputerowe"));

        when(productService.getByCategory("", Pageable.unpaged())).thenReturn(products);
        when(productService.get(anyLong())).thenReturn(new Product());

        productService.getByCategory("Podzespoły komputerowe", PageRequest.of(0, 6));
        productService.getByCategory("Gry", PageRequest.of(0, 6));

        assertEquals(productService.get(42L).getCategory(), null);

        assertEquals(productService.getByCategory(productService.get(10L).getCategory(), products.getPageable()), null);

        verify(productService, times(3)).getByCategory(any(), any());
    }

    @Test
    public void testGetProductsEnding() {

        List<Product> products = new ArrayList<>();
        products.add(new Product());
        products.add(new Product(10L, "Myszka", "Optyczna z podświetleniem RGB", 651.230, 2, "brak", ""));
        when(productService.getProductsEnding()).thenReturn(products);

        assertEquals(productService.getProductsEnding().get(0).getOrders(), null);
        assertEquals(productService.getProductsEnding().get(1).getDescription(), "Optyczna z podświetleniem RGB");
        assertThat(productService.getProductsEnding(), Matchers.hasSize(2));

        verify(productService, times(3)).getProductsEnding();
    }
}

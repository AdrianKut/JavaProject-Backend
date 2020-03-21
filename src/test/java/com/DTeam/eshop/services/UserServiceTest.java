package com.DTeam.eshop.services;

import com.DTeam.eshop.entities.User;
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

public class UserServiceTest {

    final UserService userService = mock(UserService.class);

    @Test
    public void testListAll() {

        when(userService.listAll()).thenReturn(prepareMocData());

        assertEquals(userService.listAll().get(0).getEmail(), null);
        assertEquals(userService.listAll().get(2).getPassword(), "password123");
        assertThat(userService.listAll(), Matchers.hasSize(3));

    }

    private List<User> prepareMocData() {

        List<User> users = new ArrayList<>();

        users.add(new User());
        users.add(new User());
        users.add(new User("adres@o2.pl", 32, "password123", true));

        return users;
    }

    @Test
    public void testSave() {
    }

    @Test
    public void testGet() {
    }

    @Test
    public void testDelete() {
    }

    @Test
    public void testIsUserExist() {
    }

}

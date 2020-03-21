package com.DTeam.eshop.services;

import com.DTeam.eshop.entities.Role;

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

public class RoleServiceTest {

    final RoleService roleService = mock(RoleService.class);

    @Test
    public void testListAll() {
    }

    @Test
    public void testSave() {

        when(roleService.save(any(Role.class))).thenReturn(new Role());

        Role role = roleService.save(new Role());
        role = roleService.save(new Role());

        verify(roleService, times(2)).save(role);

        assertNotEquals(role.getName(), "Janusz");
        assertEquals(role.getName(), null);

    }

    @Test
    public void testGet() {

    }

    @Test
    public void testDelete() {
        
        final Role role = new Role("");

        roleService.delete(role.getName());

        verify(roleService, times(1)).delete("");
        
    }

    @Test
    public void testIsRoleExist() {
    }

    @Test
    public void testGetByroleEmail() {
    }

}

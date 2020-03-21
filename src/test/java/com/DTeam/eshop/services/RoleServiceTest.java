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

        when(roleService.listAll()).thenReturn(prepareMocData());

        assertEquals(roleService.listAll().get(0).getName(), null);
        assertEquals(roleService.listAll().get(2).getName(), "Rola");
        assertThat(roleService.listAll(), Matchers.hasSize(3));

        verify(roleService, times(3)).listAll();

    }

    private List<Role> prepareMocData() {

        List<Role> roles = new ArrayList<>();

        roles.add(new Role());
        roles.add(new Role());
        roles.add(new Role("Rola"));

        return roles;
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

        when(roleService.get(any())).thenReturn(new Role("Amber"));

        Role role = roleService.get(new Role().getName());

        verify(roleService, times(1)).get(any());

        assertEquals(role.getName(), "Amber");

    }

    @Test
    public void testDelete() {

        final Role role = new Role("");

        roleService.delete(role.getName());

        verify(roleService, times(1)).delete("");

    }

    @Test
    public void testIsRoleExist() {

        when(roleService.isRoleExist(any())).thenReturn(true);

        final boolean result = roleService.isRoleExist("");
        assertEquals(result, true);

        verify(roleService, times(1)).isRoleExist("");
    }

    @Test
    public void testGetByroleEmail() {
    }

}

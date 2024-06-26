package com.example.hotel.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.hotel.model.Guest;
import com.example.hotel.repository.GuestRepository;
import com.example.hotel.service.GuestService;

public class GuestServiceTest {
    
    @Mock
    private GuestRepository guestRepository;

    @InjectMocks
    private GuestService guestService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllGuests() {
        Guest guest = new Guest(1L, "João", "123456789", "joao@teste.com");
        when(guestRepository.findAll()).thenReturn(Collections.singletonList(guest));
        assertEquals(1, guestService.getAllGuests().size());
    }

    @Test
    public void testGetGuestById() {
        Guest guest = new Guest(1L, "João", "123456789", "joao@teste.com");
        when(guestRepository.findById(1L)).thenReturn(Optional.of(guest));
        assertEquals("João", guestService.getGuestById(1L).getNome());
    }
}

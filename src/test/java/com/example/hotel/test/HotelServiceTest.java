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

import com.example.hotel.model.Hotel;
import com.example.hotel.repository.HotelRepository;
import com.example.hotel.service.HotelService;

public class HotelServiceTest {
    
    @Mock
    private HotelRepository hotelRepository;

    @InjectMocks
    private HotelService hotelService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllHotels() {
        Hotel hotel = new Hotel(1L, "Hotel Teste", "Localização Teste", 5, Collections.emptyList(), Collections.emptyList());
        when(hotelRepository.findAll()).thenReturn(Collections.singletonList(hotel));
        assertEquals(1, hotelService.getAllHotels().size());
    }

    @Test
    public void testGetHotelById() {
        Hotel hotel = new Hotel(1L, "Hotel Teste", "Localização Teste", 5, Collections.emptyList(), Collections.emptyList());
        when(hotelRepository.findById(1L)).thenReturn(Optional.of(hotel));
        assertEquals("Hotel Teste", hotelService.getHotelById(1L).getNome());
    }
}

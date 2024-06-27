package com.example.hotel.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.hotel.dto.HotelSearchCriteria;
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
    	Hotel hotel = new Hotel(1L, "Hotel Teste", "Localização Teste", 5,"wifi", Collections.emptyList(), Collections.emptyList());
    	when(hotelRepository.findAll()).thenReturn(Collections.singletonList(hotel));
        assertEquals(1, hotelService.getAllHotels().size());
    }

    @Test
    public void testGetHotelById() {
        Hotel hotel = new Hotel(1L, "Hotel Teste", "Localização Teste", 5,"wifi", Collections.emptyList(), Collections.emptyList());
        when(hotelRepository.findById(1L)).thenReturn(Optional.of(hotel) );
        assertEquals("Hotel Teste", hotelService.getHotelById(1L).getNome());
    }
    
    @Test
    public void testSearchHotels() {
        HotelSearchCriteria criteria = new HotelSearchCriteria();
        criteria.setDestino("Paris");
        criteria.setDataCheckin(new Date());
        criteria.setDataCheckout(new Date());
        criteria.setNumeroQuartos(2);
        criteria.setNumeroHospedes(4);

        Hotel hotel = new Hotel();
        hotel.setNome("Hotel Paris");
        hotel.setLocalizacao("Paris");

        when(hotelRepository.findHotels(
                criteria.getDestino(),
                criteria.getDataCheckin(),
                criteria.getDataCheckout(),
                criteria.getNumeroQuartos(),
                criteria.getNumeroHospedes()
        )).thenReturn(Arrays.asList(hotel));

        List<Hotel> result = hotelService.searchHotels(criteria);

        assertEquals(1, result.size());
        assertEquals("Hotel Paris", result.get(0).getNome());

        verify(hotelRepository, times(1)).findHotels(
                criteria.getDestino(),
                criteria.getDataCheckin(),
                criteria.getDataCheckout(),
                criteria.getNumeroQuartos(),
                criteria.getNumeroHospedes()
        );
    }
}

package com.example.hotel.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.Date;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.hotel.model.Reservation;
import com.example.hotel.repository.ReservationRepository;
import com.example.hotel.service.ReservationService;

public class ReservationServiceTest {
    
    @Mock
    private ReservationRepository reservationRepository;

    @InjectMocks
    private ReservationService reservationService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllReservations() {
        Reservation reservation = new Reservation(1L, new Date(), new Date(), 4, null, null, null);
        when(reservationRepository.findAll()).thenReturn(Collections.singletonList(reservation));
        assertEquals(1, reservationService.getAllReservations().size());
    }

    @Test
    public void testGetReservationById() {
        Reservation reservation = new Reservation(1L, new Date(), new Date(), 4, null, null, null);
        when(reservationRepository.findById(1L)).thenReturn(Optional.of(reservation));
        assertEquals(4, reservationService.getReservationById(1L).getAvaliacaoReserva());
    }
}

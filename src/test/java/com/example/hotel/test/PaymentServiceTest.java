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

import com.example.hotel.model.Payment;
import com.example.hotel.repository.PaymentRepository;
import com.example.hotel.service.PaymentService;

public class PaymentServiceTest {
    
    @Mock
    private PaymentRepository paymentRepository;

    @InjectMocks
    private PaymentService paymentService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllPayments() {
        Payment payment = new Payment(1L, true, new Date(), "Cartão", "comprovante123", null);
        when(paymentRepository.findAll()).thenReturn(Collections.singletonList(payment));
        assertEquals(1, paymentService.getAllPayments().size());
    }

    @Test
    public void testGetPaymentById() {
        Payment payment = new Payment(1L, true, new Date(), "Cartão", "comprovante123", null);
        when(paymentRepository.findById(1L)).thenReturn(Optional.of(payment));
        assertEquals(true, paymentService.getPaymentById(1L).isRealizado());
    }
}

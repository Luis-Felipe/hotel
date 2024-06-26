package com.example.hotel.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.hotel.model.Reservation;
import com.example.hotel.repository.ReservationRepository;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private NotificationService notificationService;

    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    public Reservation getReservationById(Long id) {
        return reservationRepository.findById(id).orElse(null);
    }

    public Reservation createReservation(Reservation reservation) {
        Reservation savedReservation = reservationRepository.save(reservation);
        notificationService.sendNotification(
                "user@example.com", 
                "Reserva Confirmada",
                "Sua reserva foi confirmada com sucesso!"
        );
        return savedReservation;
    }

    public Reservation updateReservation(Long id, Reservation reservationDetails) {
        Reservation reservation = getReservationById(id);
        if (reservation != null) {
            reservation.setDataCheckin(reservationDetails.getDataCheckin());
            reservation.setDataCheckout(reservationDetails.getDataCheckout());
            reservation.setAvaliacaoReserva(reservationDetails.getAvaliacaoReserva());
            reservation.setQuarto(reservationDetails.getQuarto());
            reservation.setHospede(reservationDetails.getHospede());
            return reservationRepository.save(reservation);
        }
        return null;
    }

    public void deleteReservation(Long id) {
        reservationRepository.deleteById(id);
    }
}

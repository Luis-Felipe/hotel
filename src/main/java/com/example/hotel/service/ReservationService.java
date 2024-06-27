package com.example.hotel.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.hotel.model.Guest;
import com.example.hotel.model.Hotel;
import com.example.hotel.model.Payment;
import com.example.hotel.model.Reservation;
import com.example.hotel.model.Room;
import com.example.hotel.repository.GuestRepository;
import com.example.hotel.repository.PaymentRepository;
import com.example.hotel.repository.ReservationRepository;
import com.example.hotel.repository.RoomRepository;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;
    
    @Autowired
    private PaymentRepository paymentRepository;
    
    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private GuestRepository guestRepository;
    
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
    
    public Reservation realizarReserva(Long hotelId, Long quartoId, Long hospedeId, Date dataCheckin, Date dataCheckout) {
        Hotel hotel = new Hotel();
        hotel.setId(hotelId);

        Room quarto = roomRepository.findById(quartoId).orElseThrow(() -> new RuntimeException("Quarto não encontrado"));

        Guest hospede = guestRepository.findById(hospedeId).orElseThrow(() -> new RuntimeException("Hóspede não encontrado"));

        Payment pagamento = new Payment();
        pagamento.setRealizado(false); // Ainda não realizado
        pagamento.setData(new Date()); // Data atual
        paymentRepository.save(pagamento);

        Reservation reserva = new Reservation();
        reserva.setHotel(hotel);
        reserva.setQuarto(quarto);
        reserva.setHospede(hospede);
        reserva.setPagamento(pagamento);
        reserva.setDataCheckin(dataCheckin);
        reserva.setDataCheckout(dataCheckout);
        reservationRepository.save(reserva);

        return reserva;
    }
    
}

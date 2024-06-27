package com.example.hotel.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.hotel.dto.HotelSearchCriteria;
import com.example.hotel.model.Hotel;
import com.example.hotel.model.Reservation;
import com.example.hotel.service.HotelService;
import com.example.hotel.service.ReservationService;

@RestController
@RequestMapping("/api/hotels")
public class HotelController {
    @Autowired
    private HotelService hotelService;

    @Autowired
    private ReservationService reservationService;
    
    @GetMapping
    public ResponseEntity<List<Hotel>> getAllHotels() {
        return ResponseEntity.ok(hotelService.getAllHotels());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Hotel> getHotelById(@PathVariable Long id) {
        Hotel hotel = hotelService.getHotelById(id);
        if (hotel == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(hotel);
    }

    @PostMapping
    public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel) {
        return ResponseEntity.ok(hotelService.saveHotel(hotel));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Hotel> updateHotel(@PathVariable Long id, @RequestBody Hotel hotelDetails) {
        Hotel hotel = hotelService.getHotelById(id);
        if (hotel == null) {
            return ResponseEntity.notFound().build();
        }
        hotel.setNome(hotelDetails.getNome());
        hotel.setLocalizacao(hotelDetails.getLocalizacao());
        hotel.setAvaliacoes(hotelDetails.getAvaliacoes());
        return ResponseEntity.ok(hotelService.saveHotel(hotel));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHotel(@PathVariable Long id) {
        hotelService.deleteHotel(id);
        return ResponseEntity.noContent().build();
    }
    
    @PostMapping("/search")
    public List<Hotel> searchHotels(@RequestBody HotelSearchCriteria criteria) {
        return hotelService.searchHotels(criteria);
    }
    
    @PostMapping("/realizar")
    public Reservation realizarReserva(@RequestParam Long hotelId,
                                   @RequestParam Long quartoId,
                                   @RequestParam Long hospedeId,
                                   @RequestParam Date dataCheckin,
                                   @RequestParam Date dataCheckout) {
    	return reservationService.realizarReserva(hotelId, quartoId, hospedeId, dataCheckin, dataCheckout);
    }
}

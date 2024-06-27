package com.example.hotel.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.hotel.dto.HotelSearchCriteria;
import com.example.hotel.model.Hotel;
import com.example.hotel.repository.HotelRepository;

@Service
public class HotelService {
    @Autowired
    private HotelRepository hotelRepository;

    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }

    public Hotel getHotelById(Long id) {
        return hotelRepository.findById(id).orElse(null);
    }

    public Hotel saveHotel(Hotel hotel) {
        return hotelRepository.save(hotel);
    }

    public void deleteHotel(Long id) {
        hotelRepository.deleteById(id);
    }
    
    
    public List<Hotel> searchHotels(HotelSearchCriteria criteria) {
        return hotelRepository.findHotels(
                criteria.getDestino(),
                criteria.getDataCheckin(),
                criteria.getDataCheckout(),
                criteria.getNumeroQuartos(),
                criteria.getNumeroHospedes()
        );
    }
    
}

package com.example.hotel.service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.hotel.model.Hotel;
import com.example.hotel.repository.HotelRepository;

@Service
public class ComparacaoService {

    @Autowired
    private HotelRepository hotelRepository;

    public List<Hotel> compararOpcoes(String localizacao, String comodidades, double maxPreco, int minAvaliacao) {
        List<Hotel> hoteis = hotelRepository.findByLocalizacao(localizacao);

        return hoteis.stream()
                .filter(hotel -> hotel.getComodidades().contains(comodidades))
                .filter(hotel -> hotel.getQuartos().stream().anyMatch(quarto -> quarto.getPreco() <= maxPreco))
                .filter(hotel -> hotel.getAvaliacoes() >= minAvaliacao)
                .sorted(Comparator.comparingInt(Hotel::getAvaliacoes).reversed())
                .collect(Collectors.toList());
    }
}

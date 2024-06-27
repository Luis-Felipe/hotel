package com.example.hotel.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.hotel.model.Hotel;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long> {
	
	List<Hotel> findByLocalizacao(String localizacao);
    

    //"AND (SELECT COUNT(q1) FROM h.quartos q1) >= :numeroQuartos"+
	
    @Query("SELECT DISTINCT h FROM Hotel h JOIN h.quartos q WHERE " +
            "(:destino IS NULL OR h.localizacao = :destino) " +
            "AND (COALESCE(:dataCheckin, :dataCheckout) BETWEEN :dataCheckin AND :dataCheckout) "+
            "and SIZE(h.quartos) >= :numeroQuartos " +
            "AND q.quantidadeHospodes >= :numeroHospedes")
    List<Hotel> findHotels(@Param("destino") String destino,
                           @Param("dataCheckin") Date dataCheckin,
                           @Param("dataCheckout") Date dataCheckout,
                           @Param("numeroQuartos") int numeroQuartos,
                           @Param("numeroHospedes") int numeroHospedes);
                           
}

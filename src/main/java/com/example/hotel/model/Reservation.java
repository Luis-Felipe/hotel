package com.example.hotel.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date dataCheckin;
    private Date dataCheckout;
    private int avaliacaoReserva;

    @ManyToOne
    @JoinColumn(name = "id_hotel")
    private Hotel hotel;

    @ManyToOne
    @JoinColumn(name = "fk_Quartos_id")
    private Room quarto;

    @ManyToOne
    @JoinColumn(name = "fk_hopedes_id")
    private Guest hospede;
    
    @ManyToOne
    @JoinColumn(name = "pagamento_id")
    private Payment pagamento;
}

package com.example.hotel.dto;

import java.util.Date;

import lombok.Data;

@Data
public class HotelSearchCriteria {

    private String destino;
    private Date dataCheckin;
    private Date dataCheckout;
    private int numeroQuartos;
    private int numeroHospedes;
}

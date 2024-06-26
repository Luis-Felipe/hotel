package com.example.hotel.test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.example.hotel.model.Hotel;
import com.example.hotel.repository.HotelRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class HotelControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private HotelRepository hotelRepository;

    @BeforeEach
    public void setUp() {
        hotelRepository.deleteAll();
    }

    @Test
    public void testGetAllHotels() throws Exception {
        hotelRepository.save(new Hotel(1L, "Hotel Teste", "Localização Teste", 5, null, null));
        mockMvc.perform(get("/hotels")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nome").value("Hotel Teste"));
    }

    @Test
    public void testGetHotelById() throws Exception {
        Hotel hotel = hotelRepository.save(new Hotel(1L, "Hotel Teste", "Localização Teste", 5, null, null));
        mockMvc.perform(get("/hotels/" + hotel.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Hotel Teste"));
    }

    @Test
    public void testCreateHotel() throws Exception {
        String hotelJson = "{\"nome\": \"Hotel Novo\", \"localozacao\": \"Localização Nova\", \"avaliacoes\": 4}";
        mockMvc.perform(post("/hotels")
                .contentType(MediaType.APPLICATION_JSON)
                .content(hotelJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Hotel Novo"));
    }

    @Test
    public void testUpdateHotel() throws Exception {
        Hotel hotel = hotelRepository.save(new Hotel(1L, "Hotel Teste", "Localização Teste", 5, null, null));
        String hotelJson = "{\"nome\": \"Hotel Atualizado\", \"localozacao\": \"Localização Atualizada\", \"avaliacoes\": 4}";
        mockMvc.perform(put("/hotels/" + hotel.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(hotelJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Hotel Atualizado"));
    }

    @Test
    public void testDeleteHotel() throws Exception {
        Hotel hotel = hotelRepository.save(new Hotel(1L, "Hotel Teste", "Localização Teste", 5, null, null));
        mockMvc.perform(delete("/hotels/" + hotel.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}

package com.example.hotel.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.hotel.model.Room;
import com.example.hotel.repository.RoomRepository;
import com.example.hotel.service.RoomService;

public class RoomServiceTest {
    
    @Mock
    private RoomRepository roomRepository;

    @InjectMocks
    private RoomService roomService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllRooms() {
        Room room = new Room(1L, 2, 100.0, "Duplo", null);
        when(roomRepository.findAll()).thenReturn(Collections.singletonList(room));
        assertEquals(1, roomService.getAllRooms().size());
    }

    @Test
    public void testGetRoomById() {
        Room room = new Room(1L, 2, 100.0, "Duplo", null);
        when(roomRepository.findById(1L)).thenReturn(Optional.of(room));
        assertEquals(100.0, roomService.getRoomById(1L).getPreco());
    }
}

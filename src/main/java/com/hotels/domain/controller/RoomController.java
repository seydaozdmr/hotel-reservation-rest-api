package com.hotels.domain.controller;

import com.hotels.domain.model.room.Room;
import com.hotels.domain.service.HotelService;
import com.hotels.domain.service.RoomService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RoomController {
    private final RoomService roomService;
    private final HotelService hotelService;

    public RoomController(RoomService roomService, HotelService hotelService) {
        this.roomService = roomService;
        this.hotelService = hotelService;
    }

    @GetMapping("/v1/rooms")
    public List<Room> getRooms(){
        return roomService.getRooms();
    }

    @GetMapping("/v1/rooms/{id}")
    public Room getRoom(@PathVariable Long id){
        return roomService.getRoom(id);
    }

    @GetMapping("/v1/{id}/rooms")
    public List<Room> getHotelRooms(@PathVariable Long id){
        return roomService.getHotelRooms(id);
    }

    @PostMapping("/v1/{id}/rooms")
    public Room addRoom(@PathVariable Long id, Room room){
        room.addHotel(hotelService.getHotel(id));
        return roomService.saveRoom(room);
    }
}

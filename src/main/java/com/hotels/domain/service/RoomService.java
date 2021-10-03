package com.hotels.domain.service;

import com.hotels.domain.model.room.Room;

import java.util.List;

public interface RoomService {
    List<Room> getRooms();

    List<Room> getHotelRooms(Long id);

    Room saveRoom(Room room);

    Room getRoom(Long id);
}

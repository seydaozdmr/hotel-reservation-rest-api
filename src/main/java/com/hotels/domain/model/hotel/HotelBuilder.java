package com.hotels.domain.model.hotel;

import com.hotels.domain.model.room.Room;

import java.util.List;

public interface HotelBuilder {
    HotelBuilder buildLocation(Location location);
    HotelBuilder buildDescription(Description description);
    HotelBuilder buildRooms(List<Room> rooms);
}

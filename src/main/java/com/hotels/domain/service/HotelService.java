package com.hotels.domain.service;

import com.hotels.domain.model.hotel.Hotel;
import com.hotels.domain.model.room.Room;

import java.util.List;

public interface HotelService {
    List<Hotel> getHotels();

    Hotel getHotel(Long id);

    Hotel saveHotel(Hotel hotel);


}

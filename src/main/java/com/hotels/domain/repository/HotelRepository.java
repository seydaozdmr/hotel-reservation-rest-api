package com.hotels.domain.repository;


import com.hotels.domain.model.hotel.Hotel;
import com.hotels.domain.model.hotel.Location;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HotelRepository extends JpaRepository<Hotel,Long> {
    List<Hotel> findAllHotelsByLocation(Location location);
}

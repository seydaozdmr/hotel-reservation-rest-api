package com.hotels.domain.service;

import com.hotels.domain.model.hotel.Hotel;
import com.hotels.domain.model.reservations.Reservations;
import com.hotels.domain.model.room.Room;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface ReservationService {
    List<Hotel> getHotelsByLocation(String location);
    Set<Hotel> getAvailableHotelsByDate(LocalDate checkIn, LocalDate checkOut);

    List<Room> getAvailableRoomsInHotel(Long id, LocalDate checkIn, LocalDate checkOut);

    Reservations makeReservation(Reservations reservations);

    List<Reservations> getAllReservations();

    Reservations getReservation(Long id);
}

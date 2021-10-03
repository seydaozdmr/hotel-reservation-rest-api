package com.hotels.domain.controller;

import com.hotels.domain.model.hotel.Hotel;
import com.hotels.domain.model.reservations.Reservations;
import com.hotels.domain.model.room.Room;
import com.hotels.domain.service.ReservationService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@RestController
public class ReservationController {
    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping("/v1/reservations")
    public List<Reservations> getAllReservations(){
        return reservationService.getAllReservations();
    }

    @GetMapping("/v1/reservations/{id}")
    public Reservations getReservation(@PathVariable Long id){
        return reservationService.getReservation(id);
    }

    @GetMapping("/v1/reservation/hotels")
    public List<Hotel> getHotelsByLocation(@RequestParam("location") String location){
        return reservationService.getHotelsByLocation(location);
    }

    @GetMapping("/v1/reservation/hotels/available")
    public Set<Hotel> getAvailableHotelsByDate(@RequestParam("checkIn")@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkIn , @RequestParam("checkOut")@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)LocalDate checkOut){
        return reservationService.getAvailableHotelsByDate(checkIn,checkOut);
    }

    @GetMapping("/v1/reservation/{id}/rooms/available")
    public List<Room> getAvailableRoomsByDate(@PathVariable Long id, @RequestParam("checkIn")@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkIn , @RequestParam("checkOut")@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)LocalDate checkOut){
        return reservationService.getAvailableRoomsInHotel(id,checkIn,checkOut);
    }

    @PostMapping("/v1/reservation/makeReservation")
    public Reservations makeReservation(@RequestBody Reservations reservations){
        return reservationService.makeReservation(reservations);
    }
}

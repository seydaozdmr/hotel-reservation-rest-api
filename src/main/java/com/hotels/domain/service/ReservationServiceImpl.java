package com.hotels.domain.service;

import com.hotels.domain.exceptions.ReservationNotAvailableException;
import com.hotels.domain.model.hotel.Hotel;
import com.hotels.domain.model.reservations.Reservations;
import com.hotels.domain.model.room.Room;
import com.hotels.domain.repository.HotelRepository;
import com.hotels.domain.repository.LocationRepository;
import com.hotels.domain.repository.ReservationsRepository;
import com.hotels.domain.repository.RoomRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class ReservationServiceImpl implements ReservationService{
    private final ReservationsRepository reservationsRepository;
    private final LocationRepository locationRepository;
    private final HotelRepository hotelRepository;
    private final RoomRepository roomRepository;

    public ReservationServiceImpl(ReservationsRepository reservationsRepository, LocationRepository locationRepository, HotelRepository hotelRepository, RoomRepository roomRepository) {
        this.reservationsRepository = reservationsRepository;
        this.locationRepository = locationRepository;
        this.hotelRepository = hotelRepository;
        this.roomRepository = roomRepository;
    }

    @Override
    public List<Hotel> getHotelsByLocation(String location){

        return hotelRepository.findAllHotelsByLocation(locationRepository.findLocationByProvince(location));
    }

    @Override
    public Set<Hotel> getAvailableHotelsByDate(LocalDate checkIn, LocalDate checkOut) {
        Set<Hotel> availableHotels=new HashSet<>();
        for(Room room:roomRepository.findAll()){
            if(room.controlIsAvailable(checkIn,checkOut)){
                availableHotels.add(room.getHotel());
            }
        }
        return availableHotels;
    }

    @Override
    public List<Room> getAvailableRoomsInHotel(Long id, LocalDate checkIn, LocalDate checkOut) {
        List<Room> availableRooms=new ArrayList<>();
        for(Room room:hotelRepository.findById(id).get().getRooms()){
            if(room.controlIsAvailable(checkIn,checkOut)){
                availableRooms.add(room);
            }

        }
        return availableRooms;
    }

    @Override
    public Reservations makeReservation(Reservations reservations) {
        synchronized (this){
            return reservationsRepository.save(reservations);
        }
    }

    @Override
    public List<Reservations> getAllReservations() {
        List<Reservations> my=reservationsRepository.findAll();
        return my;
    }

    @Override
    public Reservations getReservation(Long id) {
        return reservationsRepository.findById(id).get();
    }


}

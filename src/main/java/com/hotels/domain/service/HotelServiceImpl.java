package com.hotels.domain.service;

import com.hotels.domain.model.hotel.Hotel;
import com.hotels.domain.repository.HotelRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelServiceImpl implements HotelService{
    private final HotelRepository repository;

    public HotelServiceImpl(HotelRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Hotel> getHotels() {
        System.out.println("service");
       return repository.findAll();
    }

    @Override
    public Hotel getHotel(Long id) {
        return repository.findById(id).get();
    }

    @Override
    public Hotel saveHotel(Hotel hotel) {
        return repository.save(hotel);
    }





}

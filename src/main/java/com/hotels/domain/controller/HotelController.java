package com.hotels.domain.controller;

import com.hotels.domain.model.hotel.Hotel;
import com.hotels.domain.service.HotelService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HotelController {
    private final HotelService hotelService;

    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @GetMapping("/v1/hotels")
    public List<Hotel> getHotels(){
        return hotelService.getHotels();
    }

    @GetMapping("/v1/hotels/{id}")
    public Hotel getHotel(@PathVariable Long id){
        //TODO exception
        return hotelService.getHotel(id);
    }

    @PostMapping("/v1/hotels")
    public Hotel newHotel(@RequestBody Hotel hotel){
        return hotelService.saveHotel(hotel);
    }


}

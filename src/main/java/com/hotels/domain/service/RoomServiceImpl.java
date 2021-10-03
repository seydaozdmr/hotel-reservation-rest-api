package com.hotels.domain.service;

import com.hotels.domain.model.room.Room;
import com.hotels.domain.repository.RoomRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomServiceImpl implements RoomService{
    private final RoomRepository roomRepository;

    public RoomServiceImpl(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Override
    public List<Room> getRooms(){
        return roomRepository.findAll();
    }

    @Override
    public List<Room> getHotelRooms(Long id) {
        return roomRepository.findAllRoomsByHotelId(id);
    }

    @Override
    public Room saveRoom(Room room) {
        return roomRepository.save(room);
    }

    @Override
    public Room getRoom(Long id) {
        return roomRepository.findById(id).get();
    }
}

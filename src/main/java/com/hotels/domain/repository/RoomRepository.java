package com.hotels.domain.repository;

import com.hotels.domain.model.room.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RoomRepository extends JpaRepository<Room,Long> {
    List<Room> findAllRoomsByHotelId(Long id);

}

package com.hotels.domain.repository;

import com.hotels.domain.model.room.Bed;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BedRepository extends JpaRepository<Bed,Long> {
}

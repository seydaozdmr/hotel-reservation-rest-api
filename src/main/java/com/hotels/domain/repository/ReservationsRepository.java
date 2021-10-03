package com.hotels.domain.repository;

import com.hotels.domain.model.reservations.Reservations;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationsRepository extends JpaRepository<Reservations,Long> {
}

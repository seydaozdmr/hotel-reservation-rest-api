package com.hotels.domain.repository;

import com.hotels.domain.model.hotel.Description;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DescriptionRepository extends JpaRepository<Description,Long> {
}

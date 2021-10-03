package com.hotels.domain.repository;

import com.hotels.domain.model.hotel.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location,Long> {
    Location findLocationByProvince(String province);
}

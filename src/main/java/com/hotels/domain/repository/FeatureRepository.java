package com.hotels.domain.repository;

import com.hotels.domain.model.room.Feature;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeatureRepository extends JpaRepository<Feature,Long> {
}

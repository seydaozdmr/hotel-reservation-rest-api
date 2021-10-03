package com.hotels.domain.model.room;

import java.math.BigDecimal;
import java.util.List;

public interface FeaturedRoom {
    BigDecimal calculatePrice();
    List<Feature> featureList();
}

package com.hotels.domain.model.hotel;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Location {
    @Id
    @GeneratedValue
    private long id;
    private String province;

    @OneToOne(mappedBy = "location")
    private Hotel hotel;

    public Location() {
    }

    public Location(String province) {
        this.province = province;
    }

    public Location(long id, String province) {
        this.id = id;
        this.province = province;
    }


    public long getId() {
        return id;
    }

    public String getProvince() {
        return province;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return id == location.id && Objects.equals(province, location.province);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, province);
    }

    @Override
    public String toString() {
        return "Location{" +
                "id=" + id +
                ", province='" + province + '\'' +
                '}';
    }
}

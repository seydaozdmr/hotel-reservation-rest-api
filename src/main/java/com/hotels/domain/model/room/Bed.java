package com.hotels.domain.model.room;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
public class Bed {
    @Id
    @GeneratedValue
    private long id;
    @Enumerated(EnumType.ORDINAL)
    private BedType bedType;
    private BigDecimal price;

    @ManyToOne(fetch = FetchType.LAZY)
    private Room room;

    public Bed(){}

    public Bed(BedType bedType, BigDecimal price) {
        this.bedType = bedType;
        this.price = price;
    }

    public void addRoom(Room room){
        this.room=room;
    }

    public long getId() {
        return id;
    }

    public BedType getBedType() {
        return bedType;
    }

    public BigDecimal getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bed bed = (Bed) o;
        return id == bed.id && bedType == bed.bedType && Objects.equals(price, bed.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, bedType, price);
    }

    @Override
    public String toString() {
        return "Bed{" +
                "id=" + id +
                ", bedType=" + bedType +
                ", price=" + price +
                '}';
    }
}

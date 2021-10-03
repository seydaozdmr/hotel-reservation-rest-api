package com.hotels.domain.model.reservations;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.hotels.domain.model.room.Room;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Reservations {
    @Id
    @GeneratedValue
    private long id;

    //@JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    private Room room;

    private LocalDate checkIn;
    private LocalDate checkOut;

    public Reservations(){}

    public Reservations(Room room, LocalDate checkIn, LocalDate checkOut) {
        this.room = room;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    public long getId() {
        return id;
    }

    public Room getRoom() {
        return room;
    }

    public LocalDate getCheckIn() {
        return checkIn;
    }

    public LocalDate getCheckOut() {
        return checkOut;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reservations that = (Reservations) o;
        return id == that.id && Objects.equals(room, that.room) && Objects.equals(checkIn, that.checkIn) && Objects.equals(checkOut, that.checkOut);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, room, checkIn, checkOut);
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", room=" + room +
                ", checkIn=" + checkIn +
                ", checkOut=" + checkOut +
                '}';
    }


}

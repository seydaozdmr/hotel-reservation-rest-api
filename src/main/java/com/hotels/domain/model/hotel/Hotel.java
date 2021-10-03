package com.hotels.domain.model.hotel;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.hotels.domain.model.room.Room;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Hotel {
    @Id
    @GeneratedValue
    private long id;
    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name ="location_id",referencedColumnName = "id")
    private Location location;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name ="description_id",referencedColumnName = "id")
    private Description description;

    /**
     * Bidirectional @OneToMany
     */
    @JsonManagedReference
    @OneToMany(mappedBy = "hotel" ,cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Room> rooms;

    public Hotel(){}

    private Hotel(HotelBuild builder){
        this.id=builder.id;
        this.name=builder.name;
        this.location=builder.location;
        this.description=builder.description;
        this.rooms=builder.rooms;
        for(Room r:rooms){
            r.addHotel(this);
        }
    }

    public static class HotelBuild implements HotelBuilder{
        private long id;
        private final String name;
        private Location location=null;
        private Description description=null;
        private List<Room> rooms;

        public HotelBuild(String name) {
            this.name = name;
            rooms=new ArrayList<>();
        }


        @Override
        public HotelBuild buildLocation(Location location) {
            this.location=location;
            return this;
        }

        @Override
        public HotelBuild buildDescription(Description description) {
            this.description=description;
            return this;
        }

        @Override
        public HotelBuild buildRooms(List<Room> rooms) {
            this.rooms.addAll(rooms);

            return this;
        }

        public Hotel build(){
            return new Hotel(this);
        }


    }

    public void addRoom(Room room){
        room.addHotel(this);
        this.rooms.add(room);
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Location getLocation() {
        return location;
    }

    public Description getDescription() {
        return description;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", location=" + location +
                ", description=" + description +
                '}';
    }
}

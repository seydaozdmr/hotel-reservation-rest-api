package com.hotels.domain.model.room;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.hotels.domain.model.hotel.Hotel;
import com.hotels.domain.model.reservations.Reservations;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Room implements FeaturedRoom{
    @Id
    @GeneratedValue
    private long id;
    private String name;
    private boolean available;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private int maxCapacity=0;

    @OneToMany(mappedBy = "room",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Feature> features=new ArrayList<>();

    @OneToMany(mappedBy = "room",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Bed> beds;

    //@JsonManagedReference
    @OneToMany(mappedBy = "room",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Reservations> reservations;



    /**
     * Bidirectional @OneToMany
     */
    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    private Hotel hotel;

    public Room(){}

    public Room(String name, Bed bed) {
        this.name = name;
        if(this.beds==null){
            this.beds=new ArrayList<>();
        }
        addBed(bed);
        this.maxCapacity=beds.stream().map(b->b.getBedType().getCapacity()).reduce(0,(a,b)->a+b);
        this.available =true;
        this.reservations=new ArrayList<>();
    }

    public Room(long id, String name, boolean available, LocalDate checkIn, LocalDate checkOut, List<Feature> features, List<Bed> beds, List<Reservations> reservations, int maxCapacity, Hotel hotel) {
        this.id = id;
        this.name = name;
        this.available = available;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.features = features;
        this.beds = beds;
        this.reservations = reservations;
        this.maxCapacity = maxCapacity;
        this.hotel = hotel;
    }

    public void addBed(Bed bed){
        this.beds.add(bed);
        bed.addRoom(this);
    }

    public Hotel getHotel() {
        return hotel;
    }

    @Override
    public BigDecimal calculatePrice() {
        BigDecimal bedPrice=beds.stream().map(Bed::getPrice).reduce(BigDecimal.ZERO,BigDecimal::add);
        BigDecimal featurePrice=features.stream().map(Feature::getPrice).reduce(BigDecimal.ZERO,BigDecimal::add);
        return bedPrice.add(featurePrice);
    }

    @Override
    public List<Feature> featureList() {
        return features;
    }

    public void addFeature(Feature feature){
        this.features.add(feature);
    }

    public boolean makeReservation(LocalDate checkIn,LocalDate checkOut){
        if(checkIn.compareTo(LocalDate.now())>=0 && checkOut.compareTo(LocalDate.now().minusDays(1L))>=0){
            if(controlIsAvailable(checkIn,checkOut)){
               reservations.add(new Reservations(this,checkIn,checkOut));
               return true;
            }
        }
        return false;
    }

    public boolean controlIsAvailable(LocalDate checkIn,LocalDate checkOut){
        if(reservations.isEmpty()){
            return true;
        }
        for(Reservations r:reservations){
            if(r.getCheckIn().compareTo(checkOut)<=0 && r.getCheckOut().compareTo(checkIn)<=0)
                return true;
        }
        return false;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return id == room.id && name.equals(room.name) && beds.equals(room.beds);
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Bed> getBeds() {
        return beds;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void addHotel(Hotel hotel){
        this.hotel=hotel;
    }


    @Override
    public int hashCode() {
        return Objects.hash(id, name, beds);
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", taken=" + available +
                ", checkIn=" + checkIn +
                ", checkOut=" + checkOut +
                ", features=" + features +
                ", beds=" + beds +
                ", maxCapacity=" + maxCapacity +
                '}';
    }
}

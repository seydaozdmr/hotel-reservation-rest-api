package com.hotels.domain.model.room;


public enum BedType {
    SINGLE(1),DOUBLE(2),KING(2);
    private int capacity;

    BedType(int capacity) {
        this.capacity = capacity;
    }

    public int getCapacity(){
        return capacity;
    }
}

package com.hotels.domain.exceptions;

public class ReservationNotAvailableException extends RuntimeException{
    public ReservationNotAvailableException(String message) {
        super(message);
    }
}

package com.homeify.booking.Event;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeleteBookingEvent {

    private String tripId;
    private int deletedSeats;
}

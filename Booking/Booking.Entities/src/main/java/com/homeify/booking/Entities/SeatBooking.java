package com.homeify.booking.Entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SeatBooking {

    private String id;
    private String seatId;
    private TripBooking tripBooking;

}

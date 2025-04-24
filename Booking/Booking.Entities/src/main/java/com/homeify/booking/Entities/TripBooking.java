package com.homeify.booking.Entities;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class TripBooking {

    private String id;
    private Booking booking;
    private String tripId;
    private Long amount;

    private List<SeatBooking> seatBookings;
}

package com.homeify.booking.Entities;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class TripBooking {

    private String id;
    private String bookingId;
    private String tripId;
    private Long amount;


}

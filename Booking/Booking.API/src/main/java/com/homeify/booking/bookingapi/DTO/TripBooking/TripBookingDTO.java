package com.homeify.booking.bookingapi.DTO.TripBooking;

import com.homeify.booking.Entities.Booking;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TripBookingDTO {

    private String id;
    private String tripId;
    private Long amount;
}

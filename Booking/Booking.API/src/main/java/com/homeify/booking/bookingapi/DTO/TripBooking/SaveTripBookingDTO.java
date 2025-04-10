package com.homeify.booking.bookingapi.DTO.TripBooking;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaveTripBookingDTO {

    private String bookingId;
    private String tripId;
    private Long amount;
}

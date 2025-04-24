package com.homeify.booking.bookingapi.DTO.SeatBooking;

import com.homeify.booking.bookingapi.DTO.TripBooking.TripBookingDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SeatBookingDTO {

    private String id;
    private String seatId;
    private TripBookingDTO tripBooking;
}

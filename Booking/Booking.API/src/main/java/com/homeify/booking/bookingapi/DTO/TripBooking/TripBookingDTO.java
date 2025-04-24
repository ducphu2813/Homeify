package com.homeify.booking.bookingapi.DTO.TripBooking;

import com.homeify.booking.Entities.Booking;
import com.homeify.booking.Entities.SeatBooking;
import com.homeify.booking.bookingapi.DTO.SeatBooking.SeatBookingDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TripBookingDTO {

    private String id;
    private String tripId;
    private Long amount;

    private List<SeatBookingDTO> seatBookings;
}

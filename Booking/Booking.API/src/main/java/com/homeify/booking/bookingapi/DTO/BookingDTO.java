package com.homeify.booking.bookingapi.DTO;

import com.homeify.booking.bookingapi.DTO.TripBooking.TripBookingDTO;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class BookingDTO {

    private String id;
    private String userId;
    private LocalDateTime bookingDate;
    private Long totalAmount;
    private String status;
    private String paymentStatus;
    private LocalDateTime createdAt;

    private List<TripBookingDTO> tripBookings;

}

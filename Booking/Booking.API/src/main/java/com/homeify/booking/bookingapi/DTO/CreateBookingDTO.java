package com.homeify.booking.bookingapi.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CreateBookingDTO {

    private String tripId;
    private String userId;
    private List<String> seatId;
    private Long seatPrice;
}

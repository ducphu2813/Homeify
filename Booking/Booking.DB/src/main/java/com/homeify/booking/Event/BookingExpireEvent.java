package com.homeify.booking.Event;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class BookingExpireEvent {

    private String bookingId;
    private LocalDateTime createdAt;
}

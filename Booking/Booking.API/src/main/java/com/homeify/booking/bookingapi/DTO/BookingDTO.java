package com.homeify.booking.bookingapi.DTO;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

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

}

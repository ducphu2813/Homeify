package com.homeify.serviceinfo.serviceinfoapi.DTO.Trip;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class SaveTripDTO {

    private String tripInfoId;
    private String transportationId;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private String pickupAreaId;
    private Integer availableSeats;
    private Long seatPrice;
    private String status;
    private LocalDateTime createdAt;
}

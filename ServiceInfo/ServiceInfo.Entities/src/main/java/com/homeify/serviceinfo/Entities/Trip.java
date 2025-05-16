package com.homeify.serviceinfo.Entities;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Trip {

    private String id;
    private TripInfo tripInfo;
    private Transportation transportation;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private PickupArea pickupArea;
    private Integer availableSeats;
    private Long seatPrice;
    private String status;
    private LocalDateTime createdAt;
}

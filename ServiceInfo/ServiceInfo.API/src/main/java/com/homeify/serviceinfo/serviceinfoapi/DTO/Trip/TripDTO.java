package com.homeify.serviceinfo.serviceinfoapi.DTO.Trip;

import com.homeify.serviceinfo.Entities.PickupArea;
import com.homeify.serviceinfo.Entities.Transportation;
import com.homeify.serviceinfo.Entities.TripInfo;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class TripDTO {

    private String id;
    private TripInfo tripInfo;
    private Transportation transportation;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private PickupArea pickupArea;
    private Integer availableSeats;
    private String status;
    private LocalDateTime createdAt;
}

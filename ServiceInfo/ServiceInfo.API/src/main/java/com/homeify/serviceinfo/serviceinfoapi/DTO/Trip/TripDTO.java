package com.homeify.serviceinfo.serviceinfoapi.DTO.Trip;

import com.homeify.serviceinfo.serviceinfoapi.DTO.PickupArea.PickupAreaDTO;
import com.homeify.serviceinfo.serviceinfoapi.DTO.Transportation.TransportationDTO;
import com.homeify.serviceinfo.serviceinfoapi.DTO.TripInfo.TripInfoDTO;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class TripDTO {

    private String id;
    private TripInfoDTO tripInfo;
    private TransportationDTO transportation;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private PickupAreaDTO pickupArea;
    private Integer availableSeats;
    private String status;
    private LocalDateTime createdAt;
}

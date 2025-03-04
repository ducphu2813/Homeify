package com.homeify.serviceinfo.serviceinfoapi.DTO.TripInfo;

import com.homeify.serviceinfo.Entities.City;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TripInfoDTO {
    private String id;
    private City departureCity;
    private City arrivalCity;
    private String status;
}

package com.homeify.serviceinfo.serviceinfoapi.DTO.TripInfo;

import com.homeify.serviceinfo.serviceinfoapi.DTO.CityDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TripInfoDTO {
    private String id;
    private CityDTO departureCity;
    private CityDTO arrivalCity;
    private String status;
}

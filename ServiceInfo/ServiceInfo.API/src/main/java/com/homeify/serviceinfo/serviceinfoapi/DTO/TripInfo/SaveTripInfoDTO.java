package com.homeify.serviceinfo.serviceinfoapi.DTO.TripInfo;

import com.homeify.serviceinfo.Entities.City;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SaveTripInfoDTO {

    private String departureCityId;
    private String arrivalCityId;
    private String status;

}

package com.homeify.serviceinfo.serviceinfoapi.DTO.PickupArea;

import com.homeify.serviceinfo.Entities.City;
import com.homeify.serviceinfo.serviceinfoapi.DTO.CityDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PickupAreaDTO {

    private String id;
    private CityDTO city;
    private String number;
}

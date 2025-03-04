package com.homeify.serviceinfo.serviceinfoapi.DTO.PickupArea;

import com.homeify.serviceinfo.Entities.City;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PickupAreaDTO {

    private String id;
    private City city;
    private String number;
}

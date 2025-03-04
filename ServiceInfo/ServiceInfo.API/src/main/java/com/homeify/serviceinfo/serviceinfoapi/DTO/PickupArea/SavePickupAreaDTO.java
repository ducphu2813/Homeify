package com.homeify.serviceinfo.serviceinfoapi.DTO.PickupArea;

import com.homeify.serviceinfo.Entities.City;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SavePickupAreaDTO {

    private String cityId;
    private String number;
}

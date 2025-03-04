package com.homeify.serviceinfo.Entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TripInfo {

    private String id;
    private City departureCity;
    private City arrivalCity;
    private String status;

}

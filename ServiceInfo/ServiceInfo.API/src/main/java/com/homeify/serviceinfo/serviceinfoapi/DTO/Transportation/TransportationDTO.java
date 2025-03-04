package com.homeify.serviceinfo.serviceinfoapi.DTO.Transportation;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransportationDTO {

    private String id;
    private String name;
    private String plateNumber;
    private Integer capacity;
}

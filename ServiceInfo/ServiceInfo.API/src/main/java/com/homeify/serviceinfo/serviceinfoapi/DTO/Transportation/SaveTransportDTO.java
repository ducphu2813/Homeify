package com.homeify.serviceinfo.serviceinfoapi.DTO.Transportation;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaveTransportDTO {

    private String name;
    private String plateNumber;
    private Integer capacity;
}

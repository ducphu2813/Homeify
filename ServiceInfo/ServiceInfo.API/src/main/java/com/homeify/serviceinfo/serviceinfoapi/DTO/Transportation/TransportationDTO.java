package com.homeify.serviceinfo.serviceinfoapi.DTO.Transportation;


import com.homeify.serviceinfo.Entities.Seat;
import com.homeify.serviceinfo.serviceinfoapi.DTO.Seat.SeatDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TransportationDTO {

    private String id;
    private String name;
    private String plateNumber;
    private Integer capacity;

    private List<Seat> seats;
}

package com.homeify.serviceinfo.serviceinfoapi.DTO.Seat;

import com.homeify.serviceinfo.Entities.Transportation;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaveSeatDTO {

    private String transportationId;
    private String seatNumber;
}

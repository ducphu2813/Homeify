package com.homeify.serviceinfo.Model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "trips")
public class TripModel {

    @Id
    private String id;
    private String tripInfoId;
    private String transportationId;
    private String pickupAreaId;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private Integer availableSeats;
    private LocalDateTime createdAt;
    private String status;
}

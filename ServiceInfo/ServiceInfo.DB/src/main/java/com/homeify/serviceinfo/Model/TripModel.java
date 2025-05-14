package com.homeify.serviceinfo.Model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "trips")
@Entity
public class TripModel {

    @Id
    @Column(name = "id")
    private String id;

    //khóa ngoại đến trip info
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trip_info_id")
    private TripInfoModel tripInfo;

    //khóa ngoại đến transportation
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "transportation_id")
    private TransportationModel transportation;

    //khóa ngoại đến pickup area
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pickup_area_id")
    private PickupAreaModel pickupArea;

    @Column(name = "departure_time")
    private LocalDateTime departureTime;

    @Column(name = "arrival_time")
    private LocalDateTime arrivalTime;

    @Column(name = "available_seats")
    private Integer availableSeats;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "status")
    private String status;
}

package com.homeify.serviceinfo.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "seats")
@Entity
public class SeatModel {

    @Id
    @Column(name = "id")
    private String id;

    //khóa nghoại đến bảng transportation
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "transportation_id")
    private TransportationModel transportation;

    @Column(name = "seat_number")
    private String seatNumber;

}

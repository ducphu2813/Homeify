package com.homeify.booking.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "seatbooking")
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class SeatBookingModel {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "seat_id")
    private String seatId;

    // khóa ngoại đến bảng tripbooking
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trip_booking_id")
    private TripBookingModel tripBooking;

}

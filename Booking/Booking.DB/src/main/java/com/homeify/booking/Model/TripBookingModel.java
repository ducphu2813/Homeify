package com.homeify.booking.Model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Table(name = "tripbooking")
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class TripBookingModel {

    @Id
    @Column(name = "id")
    private String id;

    //khóa ngoại đến bảng booking
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "booking_id")
    private BookingModel booking;

    @Column(name = "trip_id")
    private String tripId;

    @Column(name = "amount")
    private Long amount;

    //liên kết đến bảng seatbooking
    @OneToMany(mappedBy = "tripBooking"
                , cascade = CascadeType.REMOVE)
    private List<SeatBookingModel> seatBookings;
}

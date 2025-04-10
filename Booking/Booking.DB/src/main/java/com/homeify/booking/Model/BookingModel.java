package com.homeify.booking.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Table(name = "booking")
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class BookingModel {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "booking_date")
    private LocalDateTime bookingDate;

    @Column(name = "total_amount")
    private Long totalAmount;

    @Column(name = "status")
    private String status;

    @Column(name = "payment_status")
    private String paymentStatus;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    //khóa ngoại đến bảng tripbooking
    @OneToMany(mappedBy = "booking"
            , cascade = CascadeType.REMOVE)
    private List<TripBookingModel> tripBookings;
}

package com.homeify.booking.Adapter;

import com.homeify.booking.Entities.TripBooking;

import java.util.List;

public interface TripBookingAdapter {

    //thêm trip booking
    TripBooking addTripBooking(TripBooking tripBooking);

    //sửa trip booking
    TripBooking updateTripBooking(TripBooking tripBooking, String id);

    //xóa trip booking
    void deleteTripBooking(String tripBookingId);

    //lấy tat ca trip booking
    List<TripBooking> getAllTripBookings();

    //tìm theo id
    TripBooking findTripBookingById(String tripBookingId);

    //tìm theo tripId
    List<TripBooking> findTripBookingsByTripId(String tripId);

    // Tìm TripBooking theo tripId và bookingId
    TripBooking findTripBookingsByTripIdAndBookingId(String tripId, String bookingId);

    //đếm số ghế được đặt theo trip booking id
    int countSeatsByTripBookingId(String tripBookingId);

    //tìm theo booking id
    List<TripBooking> findTripBookingsByBookingId(String bookingId);
}

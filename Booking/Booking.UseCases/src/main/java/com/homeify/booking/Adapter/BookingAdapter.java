package com.homeify.booking.Adapter;

import com.homeify.booking.Entities.Booking;

import java.util.List;

public interface BookingAdapter {

    //thêm booking
    Booking addBooking(Booking booking);

    //sửa booking
    Booking updateBooking(Booking booking, String id);

    //xóa booking
    void deleteBooking(String bookingId);

    //lấy tat ca booking
    List<Booking> getAllBookings();

    //tìm theo id
    Booking findBookingById(String bookingId);
}

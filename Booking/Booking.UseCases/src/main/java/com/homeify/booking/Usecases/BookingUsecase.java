package com.homeify.booking.Usecases;

import com.homeify.booking.Adapter.BookingAdapter;
import com.homeify.booking.Entities.Booking;

import java.util.List;

public class BookingUsecase {

    private final BookingAdapter bookingAdapter;

    public BookingUsecase(BookingAdapter bookingAdapter) {
        this.bookingAdapter = bookingAdapter;
    }

    //teh6m booking
    public Booking addBooking(Booking booking) {
        return bookingAdapter.addBooking(booking);
    }

    //sửa booking
    public Booking updateBooking(Booking booking, String id) {
        return bookingAdapter.updateBooking(booking, id);
    }

    //xóa booking
    public void deleteBooking(String bookingId) {
        bookingAdapter.deleteBooking(bookingId);
    }

    //lấy tat ca booking
    public List<Booking> getAllBookings() {
        return bookingAdapter.getAllBookings();
    }

    //tìm theo id
    public Booking findBookingById(String bookingId) {
        return bookingAdapter.findBookingById(bookingId);
    }
}

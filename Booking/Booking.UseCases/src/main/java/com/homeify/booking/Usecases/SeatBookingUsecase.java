package com.homeify.booking.Usecases;

import com.homeify.booking.Adapter.SeatBookingAdapter;
import com.homeify.booking.Entities.SeatBooking;

import java.util.List;

public class SeatBookingUsecase {

    private final SeatBookingAdapter seatBookingAdapter;

    public SeatBookingUsecase(SeatBookingAdapter seatBookingAdapter) {
        this.seatBookingAdapter = seatBookingAdapter;
    }

    //thêm seat booking
    public SeatBooking addSeatBooking(SeatBooking seatBooking) {
        return seatBookingAdapter.addSeatBooking(seatBooking);
    }

    //sửa seat booking
    public SeatBooking updateSeatBooking(SeatBooking seatBooking, String id) {
        return seatBookingAdapter.updateSeatBooking(seatBooking, id);
    }

    //xóa seat booking
    public void deleteSeatBooking(String seatBookingId) {
        seatBookingAdapter.deleteSeatBooking(seatBookingId);
    }

    //lấy tất cả seat booking
    public List<SeatBooking> getAllSeatBookings() {
        return seatBookingAdapter.getAllSeatBookings();
    }

    //tìm theo id
    public SeatBooking findSeatBookingById(String seatBookingId) {
        return seatBookingAdapter.findSeatBookingById(seatBookingId);
    }
}

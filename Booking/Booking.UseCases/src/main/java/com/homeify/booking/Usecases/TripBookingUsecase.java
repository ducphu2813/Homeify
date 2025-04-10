package com.homeify.booking.Usecases;

import com.homeify.booking.Entities.TripBooking;
import com.homeify.booking.Adapter.TripBookingAdapter;

import java.util.List;

public class TripBookingUsecase {

    private final TripBookingAdapter tripBookingAdapter;

    public TripBookingUsecase(TripBookingAdapter tripBookingAdapter) {
        this.tripBookingAdapter = tripBookingAdapter;
    }

    //thêm trip booking
    public TripBooking addTripBooking(TripBooking tripBooking) {
        return tripBookingAdapter.addTripBooking(tripBooking);
    }

    //sửa trip booking
    public TripBooking updateTripBooking(TripBooking tripBooking, String id) {
        return tripBookingAdapter.updateTripBooking(tripBooking, id);
    }

    //xóa trip booking
    public void deleteTripBooking(String tripBookingId) {
        tripBookingAdapter.deleteTripBooking(tripBookingId);
    }

    //lấy tất cả trip booking
    public List<TripBooking> getAllTripBookings() {
        return tripBookingAdapter.getAllTripBookings();
    }

    //tìm theo id
    public TripBooking findTripBookingById(String tripBookingId) {
        return tripBookingAdapter.findTripBookingById(tripBookingId);
    }
}

package com.homeify.booking.Adapter;

import com.homeify.booking.Entities.SeatBooking;

import java.util.List;

public interface SeatBookingAdapter {

    //thêm seat booking
    SeatBooking addSeatBooking(SeatBooking seatBooking);

    //sửa seat booking
    SeatBooking updateSeatBooking(SeatBooking seatBooking, String id);

    //xóa seat booking
    void deleteSeatBooking(String seatBookingId);

    //lấy tất cả seat booking
    List<SeatBooking> getAllSeatBookings();

    //tìm theo id
    SeatBooking findSeatBookingById(String seatBookingId);

    // Tìm tất cả SeatBooking theo danh sách seatId và danh sách tripBookingId
    List<SeatBooking> findConflictingSeats(List<String> seatIds, List<String> tripBookingIds);

    // Kiểm tra những seat_id đã tồn tại trong các TripBooking cùng tripId
    List<SeatBooking> findExistingBookedSeats(List<String> tripBookingIds, List<String> seatIds);

    //tìm các SeatBooking theo tripBookingId và List seatId
    List<SeatBooking> findByTripBookingIdAndSeatId(String tripBookingId, List<String> seatIds);

    //lấy danh sách id seats đã đặt theo tripId
    List<String> findBookedSeatIdsByTripId(String tripId);

}

package com.homeify.booking.Repository;

import com.homeify.booking.Model.TripBookingModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TripBookingRepository extends JpaRepository<TripBookingModel, String> {
    void deleteById(String id);

    //tìm theo danh sách id
    List<TripBookingModel> findByIdIn(List<String> ids);

    // Tìm các TripBooking theo tripId
    List<TripBookingModel> findByTripId(String tripId);

    // Tìm TripBooking theo tripId và userId (đi qua Booking)
    @Query("SELECT tb FROM TripBookingModel tb " +
            "JOIN tb.booking b " +
            "WHERE tb.tripId = :tripId AND b.userId = :userId")
    List<TripBookingModel> findByTripIdAndUserId(@Param("tripId") String tripId,
                                                 @Param("userId") String userId);

    // Tìm TripBooking theo tripId và bookingId
    @Query("SELECT tb FROM TripBookingModel tb " +
            "JOIN tb.booking b " +
            "WHERE tb.tripId = :tripId AND b.id = :bookingId")
    TripBookingModel findByTripIdAndBookingId(@Param("tripId") String tripId,
                                              @Param("bookingId") String bookingId);

    //đếm số ghế được đặt theo trip booking id
    @Query("SELECT COUNT(sb) FROM SeatBookingModel sb " +
            "JOIN sb.tripBooking tb " +
            "WHERE tb.id = :tripBookingId")
    int countSeatsByTripBookingId(@Param("tripBookingId") String tripBookingId);

    //tìm theo booking id
    @Query("SELECT tb FROM TripBookingModel tb " +
            "JOIN tb.booking b " +
            "WHERE b.id = :bookingId")
    List<TripBookingModel> findByBookingId(@Param("bookingId") String bookingId);
}

package com.homeify.booking.Repository;

import com.homeify.booking.Model.SeatBookingModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeatBookingRepository extends JpaRepository<SeatBookingModel, String> {
    void deleteById(String id);

    // tìm theo danh sách id
    List<SeatBookingModel> findByIdIn(List<String> ids);

    // Tìm tất cả SeatBooking theo danh sách seatId và danh sách tripBookingId
    @Query("SELECT s FROM SeatBookingModel s WHERE s.seatId IN :seatIds AND s.tripBooking.id IN :tripBookingIds")
    List<SeatBookingModel> findConflictingSeats(@Param("seatIds") List<String> seatIds,
                                                @Param("tripBookingIds") List<String> tripBookingIds);


    // Kiểm tra những seat_id đã tồn tại trong các TripBooking cùng tripId
    @Query("SELECT sb FROM SeatBookingModel sb WHERE sb.tripBooking.id IN :tripBookingIds AND sb.seatId IN :seatIds")
    List<SeatBookingModel> findExistingBookedSeats(@Param("tripBookingIds") List<String> tripBookingIds, @Param("seatIds") List<String> seatIds);

    //tìm các SeatBooking theo 1 tripBookingId và List seatId
    @Query("SELECT sb FROM SeatBookingModel sb WHERE sb.tripBooking.id = :tripBookingId AND sb.seatId IN :seatIds")
    List<SeatBookingModel> findByTripBookingIdAndSeatId(@Param("tripBookingId") String tripBookingId, @Param("seatIds") List<String> seatIds);
}

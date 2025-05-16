package com.homeify.booking.Repository;

import com.homeify.booking.Model.BookingModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<BookingModel, String> {
    void deleteById(String id);

    //tìm theo danh sách id
    List<BookingModel> findByIdIn(List<String> ids);

    // Đếm số booking theo tháng và năm của bookingDate
    @Query(value = "SELECT COUNT(*) FROM booking b WHERE EXTRACT(MONTH FROM b.booking_date) = :month AND EXTRACT(YEAR FROM b.booking_date) = :year", nativeQuery = true)
    int countByMonthAndYear(@Param("month") int month, @Param("year") int year);

    //tìm booking theo user id
    @Query("SELECT b FROM BookingModel b WHERE b.userId = :userId")
    List<BookingModel> findByUserId(@Param("userId") String userId);

    // Tìm Booking theo userId và đã có chuyến đi tripId đó
    @Query("SELECT b FROM BookingModel b JOIN b.tripBookings tb WHERE b.userId = :userId AND tb.tripId = :tripId")
    BookingModel findBookingByUserIdAndTripId(@Param("userId") String userId, @Param("tripId") String tripId);

    //lấy trip id và số ghế dã đặt theo booking id
    @Query(value = """
        SELECT tb.trip_id, COUNT(sb.id) AS seat_count
        FROM tripbooking tb
        JOIN seatbooking sb ON tb.id = sb.trip_booking_id
        WHERE tb.booking_id = :bookingId
        GROUP BY tb.trip_id
    """, nativeQuery = true)
    List<Object[]> findTripIdAndSeatCountByBookingId(@Param("bookingId") String bookingId);

}

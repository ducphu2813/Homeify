package com.homeify.serviceinfo.Repository;

import com.homeify.serviceinfo.Model.TripModel;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TripRepository extends JpaRepository<TripModel, String> {

    //tìm id cuối cùng
    @Query("SELECT t FROM TripModel t ORDER BY t.id DESC LIMIT 1")
    TripModel findTopByOrderByIdDesc();

    //tìm theo nhiều id
    List<TripModel> findByIdIn(List<String> ids);

    //tìm theo >= departureTime và danh sách trip info id
    @Query("""
        SELECT t FROM TripModel t
        WHERE t.departureTime >= :departureTime AND t.tripInfo.id IN :tripInfoIds
    """)
    List<TripModel> findByDepartureTimeGreaterThanEqualAndTripInfoIdIn(List<String> tripInfoIds, LocalDateTime departureTime);

    //kiểm tra tồn tại theo trip info id và departure time
    @Query("""
        SELECT CASE WHEN COUNT(t) > 0 THEN true ELSE false END
        FROM TripModel t
        WHERE t.tripInfo.id = :tripInfoId AND t.departureTime = :departureTime
    """)
    boolean existsByTripInfoIdAndDepartureTime(String tripInfoId, LocalDateTime departureTime);

    //tìm các chuyến cùng thời gian(để kiểm tra xe có trùng lịch hay không)
    @Query("""
        SELECT t FROM TripModel t
        WHERE t.departureTime < :newArrivalTime AND t.arrivalTime > :newDepartureTime
    """)
    List<TripModel> findBusyTrips(@Param("newDepartureTime") LocalDateTime newDepartureTime,
                                  @Param("newArrivalTime") LocalDateTime newArrivalTime);

    //cập nhật số ghế đã đặt cho chuyến đi
    @Modifying
    @Transactional
    @Query("""
        UPDATE TripModel t
        SET t.availableSeats =
            CASE 
                WHEN :action = 'delete' THEN t.availableSeats + :seatCount
                WHEN :action = 'book' THEN t.availableSeats - :seatCount
                ELSE t.availableSeats
            END
        WHERE t.id = :tripId
    """)
    void updateAvailableSeats(@Param("tripId") String tripId,
                              @Param("seatCount") int seatCount,
                              @Param("action") String action);
}

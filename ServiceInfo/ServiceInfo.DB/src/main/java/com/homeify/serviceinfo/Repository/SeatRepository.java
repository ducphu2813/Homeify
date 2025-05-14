package com.homeify.serviceinfo.Repository;

import com.homeify.serviceinfo.Model.SeatModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeatRepository extends JpaRepository<SeatModel, String> {


    //tìm id cuối cùng
    @Query(value = "SELECT * FROM seats ORDER BY id DESC LIMIT 1", nativeQuery = true)
    SeatModel findTopByOrderByIdDesc();

    //lấy danh sách theo danh sách id
    @Query("SELECT s FROM SeatModel s WHERE s.id IN :ids")
    List<SeatModel> findAllByIdIn(List<String> ids);

    //lấy danh sách theo transportationId
    @Query("SELECT s FROM SeatModel s WHERE s.transportation.id = :transportationId")
    List<SeatModel> findAllByTransportationId(String transportationId);
}

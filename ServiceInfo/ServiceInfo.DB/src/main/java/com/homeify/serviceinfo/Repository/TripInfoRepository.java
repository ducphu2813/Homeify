package com.homeify.serviceinfo.Repository;

import com.homeify.serviceinfo.Model.TripInfoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TripInfoRepository extends JpaRepository<TripInfoModel, String> {

    //tìm id cuối cùng
    @Query(value = "SELECT * FROM trip_infos ORDER BY id DESC LIMIT 1", nativeQuery = true)
    TripInfoModel findTopByOrderByIdDesc();

    //tìm theo danh sách id
    @Query("SELECT t FROM TripInfoModel t WHERE t.id IN :ids")
    List<TripInfoModel> findByIdIn(List<String> ids);

    //tiìm theo departureCityId và arrivalCityId
    @Query("SELECT t FROM TripInfoModel t WHERE t.departureCity.id = :departureCityId AND t.arrivalCity.id = :arrivalCityId")
    List<TripInfoModel> findByDepartureCityIdAndArrivalCityId(String departureCityId, String arrivalCityId);
}

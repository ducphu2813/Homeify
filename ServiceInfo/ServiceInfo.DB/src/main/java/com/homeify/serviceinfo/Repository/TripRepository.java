package com.homeify.serviceinfo.Repository;

import com.homeify.serviceinfo.Model.TripModel;
import com.homeify.serviceinfo.Repository.Custom.Interface.TripRepositoryCustom;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TripRepository extends MongoRepository<TripModel, String>, TripRepositoryCustom {
    //tìm id cuối cùng
    TripModel findTopByOrderByIdDesc();

    //tìm theo nhiều id
    List<TripModel> findByIdIn(List<String> ids);

    //tìm theo >= departureTime và danh sách trip info id
    List<TripModel> findByTripInfoIdInAndDepartureTimeAfter(List<String> tripInfoIds, LocalDateTime departureTime);

    //kiểm tra tồn tại theo trip info id và departure time
    boolean existsByTripInfoIdAndDepartureTime(String tripInfoId, LocalDateTime departureTime);

    //tìm cách chuyến cùng thời gian(để kiểm tra xe có trùng lịch hay không)
    @Query("""
    {
      "$and": [
        {
          "departureTime": { "$lt": ?1 },
          "arrivalTime": { "$gt": ?0 }
        }
      ]
    }
    """)
    List<TripModel> findBusyTrips(LocalDateTime newDepartureTime, LocalDateTime newArrivalTime);
}

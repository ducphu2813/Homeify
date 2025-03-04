package com.homeify.serviceinfo.Repository;

import com.homeify.serviceinfo.Model.TripModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TripRepository extends MongoRepository<TripModel, String> {
    //tìm id cuối cùng
    TripModel findTopByOrderByIdDesc();

    //tìm theo nhiều id
    List<TripModel> findByIdIn(List<String> ids);
}

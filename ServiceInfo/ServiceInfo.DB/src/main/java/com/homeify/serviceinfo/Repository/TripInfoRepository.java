package com.homeify.serviceinfo.Repository;

import com.homeify.serviceinfo.Model.TripInfoModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TripInfoRepository extends MongoRepository<TripInfoModel, String> {
    //tìm id cuối cùng
    TripInfoModel findTopByOrderByIdDesc();

    //tìm theo danh sách id
    List<TripInfoModel> findByIdIn(List<String> ids);
}

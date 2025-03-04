package com.homeify.serviceinfo.Repository;

import com.homeify.serviceinfo.Model.CityModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends MongoRepository<CityModel, String> {
    //tìm id cuối cùng
    CityModel findTopByOrderByIdDesc();

    //lấy danh sách theo danh sách id
    List<CityModel> findAllByIdIn(List<String> ids);
}

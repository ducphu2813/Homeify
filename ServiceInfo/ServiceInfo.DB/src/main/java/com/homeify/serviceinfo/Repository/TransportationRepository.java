package com.homeify.serviceinfo.Repository;

import com.homeify.serviceinfo.Model.TransportationModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransportationRepository extends MongoRepository<TransportationModel, String> {
    //tìm id cuối cùng
    TransportationModel findTopByOrderByIdDesc();

    //lấy danh sách theo danh sách id
    List<TransportationModel> findAllByIdIn(List<String> ids);
}

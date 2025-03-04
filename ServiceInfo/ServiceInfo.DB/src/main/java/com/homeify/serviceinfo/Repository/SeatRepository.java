package com.homeify.serviceinfo.Repository;

import com.homeify.serviceinfo.Model.SeatModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeatRepository extends MongoRepository<SeatModel, String> {
    //tìm id cuối cùng
    SeatModel findTopByOrderByIdDesc();

    //lấy danh sách theo danh sách id
    List<SeatModel> findAllByIdIn(List<String> ids);
}

package com.homeify.serviceinfo.Repository;

import com.homeify.serviceinfo.Model.PickupAreaModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PickupAreaRepository extends MongoRepository<PickupAreaModel, String> {
    PickupAreaModel findTopByOrderByIdDesc();
    List<PickupAreaModel> findByIdIn(List<String> ids);
}

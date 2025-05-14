package com.homeify.serviceinfo.Repository;

import com.homeify.serviceinfo.Model.PickupAreaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PickupAreaRepository extends JpaRepository<PickupAreaModel, String> {

    @Query(value = "SELECT * FROM pickup_areas ORDER BY id DESC LIMIT 1", nativeQuery = true)
    PickupAreaModel findTopByOrderByIdDesc();

    //tìm theo danh sách id
    @Query("SELECT p FROM PickupAreaModel p WHERE p.id IN :ids")
    List<PickupAreaModel> findByIdIn(List<String> ids);
}

package com.homeify.serviceinfo.Repository;

import com.homeify.serviceinfo.Model.CityModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends JpaRepository<CityModel, String> {

    //tìm id cuối cùng
    @Query(value = "SELECT * FROM cities ORDER BY id DESC LIMIT 1", nativeQuery = true)
    CityModel findTopByOrderByIdDesc();

    //lấy danh sách theo danh sách id
    @Query("SELECT c FROM CityModel c WHERE c.id IN :ids")
    List<CityModel> findAllByIdIn(List<String> ids);
}

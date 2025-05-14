package com.homeify.serviceinfo.Repository;

import com.homeify.serviceinfo.Model.TransportationModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransportationRepository extends JpaRepository<TransportationModel, String> {


    //tìm id cuối cùng
    @Query(value = "SELECT * FROM transportations ORDER BY id DESC LIMIT 1", nativeQuery = true)
    TransportationModel findTopByOrderByIdDesc();

    //lấy danh sách theo danh sách id
    @Query("SELECT t FROM TransportationModel t WHERE t.id IN :ids")
    List<TransportationModel> findAllByIdIn(List<String> ids);
}

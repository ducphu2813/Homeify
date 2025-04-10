package com.homeify.booking.Repository;

import com.homeify.booking.Model.TripBookingModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TripBookingRepository extends JpaRepository<TripBookingModel, String> {
    void deleteById(String id);

    //tìm theo danh sách id
    List<TripBookingModel> findByIdIn(List<String> ids);
}

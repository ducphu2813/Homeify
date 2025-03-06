package com.homeify.booking.Repository;

import com.homeify.booking.Model.BookingModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<BookingModel, String> {
    void deleteById(String id);

    //tìm theo danh sách id
    List<BookingModel> findByIdIn(List<String> ids);
}

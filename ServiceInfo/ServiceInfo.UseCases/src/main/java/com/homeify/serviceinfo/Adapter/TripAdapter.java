package com.homeify.serviceinfo.Adapter;

import com.homeify.serviceinfo.Entities.Trip;

import java.time.LocalDateTime;
import java.util.List;

public interface TripAdapter {

    //thêm trip
    Trip addTrip(Trip trip);

    //sửa trip
    Trip updateTrip(Trip trip, String tripId);

    //xóa trip
    void deleteTrip(String tripId);

    //lấy tat ca trip
    List<Trip> getAllTrip();

    //tìm theo id
    Trip findTripById(String tripId);

    //tìm theo departureCityId và arrivalCityId và >= departureDate
    List<Trip> findByDepartureCityIdAndArrivalCityIdAndDepartureDateGreaterThanEqual(String departureCityId, String arrivalCityId, LocalDateTime departureDate);

    //cập nhật số ghế trống cho trip theo trip id, nhận vào trip id và số ghế trống và action "delete" hoặc "book"
    void updateAvailableSeats(String tripId, int seatCount, String action);
}

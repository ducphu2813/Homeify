package com.homeify.serviceinfo.Adapter;

import com.homeify.serviceinfo.Entities.Trip;

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
}

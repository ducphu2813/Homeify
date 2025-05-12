package com.homeify.serviceinfo.UseCases;

import com.homeify.serviceinfo.Adapter.TripAdapter;
import com.homeify.serviceinfo.Entities.Trip;

import java.time.LocalDateTime;
import java.util.List;

public class TripUsecase {

    private final TripAdapter tripAdapter;

    public TripUsecase(TripAdapter tripAdapter) {
        this.tripAdapter = tripAdapter;
    }

    //thêm trip
    public Trip addTrip(Trip trip) {
        return tripAdapter.addTrip(trip);
    }

    //sửa trip
    public Trip updateTrip(Trip trip, String tripId) {
        return tripAdapter.updateTrip(trip, tripId);
    }

    //xóa trip
    public void deleteTrip(String tripId) {
        tripAdapter.deleteTrip(tripId);
    }

    //tìm theo id
    public Trip findTripById(String tripId) {
        return tripAdapter.findTripById(tripId);
    }

    //lấy tat ca trip
    public List<Trip> getAllTrip() {
        return tripAdapter.getAllTrip();
    }

    //tìm theo departureCityId và arrivalCityId và >= departureDate
    public List<Trip> findByDepartureCityIdAndArrivalCityIdAndDepartureDateGreaterThanEqual(String departureCityId, String arrivalCityId, LocalDateTime departureDate) {
        return tripAdapter.findByDepartureCityIdAndArrivalCityIdAndDepartureDateGreaterThanEqual(departureCityId, arrivalCityId, departureDate);
    }

    //cập nhật cập nhật số ghế trống cho trip theo trip id, nhận vào trip id và số ghế trống và action "delete" hoặc "book"
    public void updateAvailableSeats(String tripId, int seatCount, String action) {
        tripAdapter.updateAvailableSeats(tripId, seatCount, action);
    }
}

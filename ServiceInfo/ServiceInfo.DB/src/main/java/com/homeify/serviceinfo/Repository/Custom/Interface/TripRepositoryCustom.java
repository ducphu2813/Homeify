package com.homeify.serviceinfo.Repository.Custom.Interface;

public interface TripRepositoryCustom {
    void updateAvailableSeats(String tripId, int seatCount, String action);
}
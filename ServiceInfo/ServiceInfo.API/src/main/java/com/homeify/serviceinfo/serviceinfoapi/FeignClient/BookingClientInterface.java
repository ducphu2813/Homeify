package com.homeify.serviceinfo.serviceinfoapi.FeignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient("Booking.API")
public interface BookingClientInterface {

    @GetMapping("/internal/seat-booking/get-booked-seat-ids/{tripId}")
    public List<String> findBookedSeatIdsByTripId(@PathVariable String tripId);

}

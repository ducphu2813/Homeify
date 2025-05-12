package com.homeify.booking.bookingapi.FeignClient;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@FeignClient("ServiceInfo.API")
public interface TripClientInterface {

    //lấy số ghế còn trống của chuyến đi
    @GetMapping("/internal/trip/getAvailableSeats/{tripId}")
    public int getAvailableSeats(@PathVariable String tripId);

    //cập nhật số ghế trống cho chuyến đi
    @PostMapping("/internal/trip/updateBookedSeats")
    public void updateBookedSeats( @RequestBody Map<String, Object> payload);
}

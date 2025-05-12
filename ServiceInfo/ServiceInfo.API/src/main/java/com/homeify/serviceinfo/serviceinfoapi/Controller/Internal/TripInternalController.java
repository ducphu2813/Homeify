package com.homeify.serviceinfo.serviceinfoapi.Controller.Internal;


import com.homeify.serviceinfo.UseCases.TripUsecase;
import com.homeify.serviceinfo.serviceinfoapi.Mapper.TripDTOMapper;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/internal/trip")
public class TripInternalController {

    private final TripUsecase tripUsecase;

    public TripInternalController(TripUsecase tripUsecase) {
        this.tripUsecase = tripUsecase;
    }

    //lấy số lượng ghế còn trống theo trip id
    @GetMapping("/getAvailableSeats/{tripId}")
    public int getAvailableSeats(@PathVariable String tripId) {
        return tripUsecase.findTripById(tripId).getAvailableSeats();
    }

    //cập nhật số lượng ghế sau khi đặt vé
    @PostMapping("/updateBookedSeats")
    public void updateBookedSeats( @RequestBody Map<String, Object> payload) {

        String tripId = (String) payload.get("tripId");
        int bookedSeats = (int) payload.get("bookedSeats");
        String action = (String) payload.get("action");

        tripUsecase.updateAvailableSeats(tripId, bookedSeats, action);
    }


}

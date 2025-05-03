package com.homeify.booking.bookingapi.Controller.Internal;

import com.homeify.booking.Usecases.SeatBookingUsecase;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/internal/seat-booking")
public class SeatBookingInternal {


    private final SeatBookingUsecase seatBookingUsecase;

    public SeatBookingInternal(SeatBookingUsecase seatBookingUsecase) {
        this.seatBookingUsecase = seatBookingUsecase;
    }

    //lấy danh sách id seats đã đặt theo tripId
    @GetMapping("/get-booked-seat-ids/{tripId}")
    public List<String> findBookedSeatIdsByTripId(@PathVariable String tripId) {
        return seatBookingUsecase.findBookedSeatIdsByTripId(tripId);
    }


}

package com.homeify.booking.bookingapi.Controller;


import com.homeify.booking.Entities.Booking;
import com.homeify.booking.Entities.TripBooking;
import com.homeify.booking.Usecases.TripBookingUsecase;
import com.homeify.booking.bookingapi.DTO.TripBooking.SaveTripBookingDTO;
import com.homeify.booking.bookingapi.DTO.TripBooking.TripBookingDTO;
import com.homeify.booking.bookingapi.Mapper.TripBookingDTOMapper;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/trip-booking")
public class TripBookingController {

    private final TripBookingUsecase tripBookingUsecase;
    private final TripBookingDTOMapper tripBookingDTOMapper;

    public TripBookingController(TripBookingUsecase tripBookingUsecase, TripBookingDTOMapper tripBookingDTOMapper) {
        this.tripBookingUsecase = tripBookingUsecase;
        this.tripBookingDTOMapper = tripBookingDTOMapper;
    }

    //get all
    @RequestMapping("/getAll")
    public List<TripBookingDTO> getAll() {
        
        List<TripBooking> tripBookings = tripBookingUsecase.getAllTripBookings();

        //chuyển từ List<TripBooking> sang List<TripBookingDTO>
        //dùng mapper
        List<TripBookingDTO> tripBookingDTOs = tripBookingDTOMapper.toTripBookingDTOs(tripBookings);

        return tripBookingDTOs;
    }

    //thêm
    @RequestMapping("/add")
    public TripBookingDTO add(@RequestBody SaveTripBookingDTO tripBookingDTO) {
        TripBooking tripBooking = tripBookingDTOMapper.toTripBooking(tripBookingDTO);

        Booking booking = new Booking();
        booking.setId(tripBookingDTO.getBookingId());

        tripBooking.setBooking(booking);

        tripBooking = tripBookingUsecase.addTripBooking(tripBooking);

        return tripBookingDTOMapper.toTripBookingDTO(tripBooking);
    }

    //sửa
    @RequestMapping("/update/{id}")
    public TripBookingDTO update(@RequestBody SaveTripBookingDTO tripBookingDTO, @PathVariable String id) {
        TripBooking tripBooking = tripBookingDTOMapper.toTripBooking(tripBookingDTO);

        tripBooking = tripBookingUsecase.updateTripBooking(tripBooking, id);

        return tripBookingDTOMapper.toTripBookingDTO(tripBooking);
    }

    //xóa
    @RequestMapping("/delete/{id}")
    public void delete(@PathVariable String id) {
        tripBookingUsecase.deleteTripBooking(id);
    }

    //tìm theo id
    @RequestMapping("/find/{id}")
    public TripBookingDTO findById(@PathVariable String id) {
        TripBooking tripBooking = tripBookingUsecase.findTripBookingById(id);

        return tripBookingDTOMapper.toTripBookingDTO(tripBooking);
    }
}

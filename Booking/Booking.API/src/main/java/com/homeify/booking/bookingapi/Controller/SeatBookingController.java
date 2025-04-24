package com.homeify.booking.bookingapi.Controller;


import com.homeify.booking.Entities.SeatBooking;
import com.homeify.booking.Entities.TripBooking;
import com.homeify.booking.Usecases.SeatBookingUsecase;
import com.homeify.booking.bookingapi.DTO.SeatBooking.SaveSeatBookingDTO;
import com.homeify.booking.bookingapi.DTO.SeatBooking.SeatBookingDTO;
import com.homeify.booking.bookingapi.Mapper.SeatBookingDTOMapper;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/seat-booking")
public class SeatBookingController {

    private final SeatBookingUsecase seatBookingUsecase;
    private final SeatBookingDTOMapper seatBookingDTOMapper;

    public SeatBookingController(SeatBookingUsecase seatBookingUsecase, SeatBookingDTOMapper seatBookingDTOMapper) {
        this.seatBookingUsecase = seatBookingUsecase;
        this.seatBookingDTOMapper = seatBookingDTOMapper;
    }

    //thêm
    @RequestMapping("/add")
    public SeatBookingDTO add(@RequestBody SaveSeatBookingDTO seatBookingDTO) {

        //chuyển từ SaveSeatBookingDTO sang SeatBooking
        SeatBooking seatBooking = seatBookingDTOMapper.toSeatBooking(seatBookingDTO);

        //set id của trip booking
        TripBooking tripBooking = new TripBooking();
        tripBooking.setId(seatBookingDTO.getTripBookingId());

        seatBooking.setTripBooking(tripBooking);

        seatBooking = seatBookingUsecase.addSeatBooking(seatBooking);

        //chuyển từ SeatBooking sang SeatBookingDTO
        return seatBookingDTOMapper.toSeatBookingDTO(seatBooking);
    }

    //sửa
    @RequestMapping("/update/{id}")
    public SeatBookingDTO update(@RequestBody SaveSeatBookingDTO seatBookingDTO, @PathVariable String id) {

        //chuyển từ SaveSeatBookingDTO sang SeatBooking
        SeatBooking seatBooking = seatBookingDTOMapper.toSeatBooking(seatBookingDTO);

        //set id của trip booking
        TripBooking tripBooking = new TripBooking();
        tripBooking.setId(seatBookingDTO.getTripBookingId());

        seatBooking.setTripBooking(tripBooking);

        seatBooking = seatBookingUsecase.updateSeatBooking(seatBooking, id);

        //chuyển từ SeatBooking sang SeatBookingDTO
        return seatBookingDTOMapper.toSeatBookingDTO(seatBooking);
    }

    //xóa
    @RequestMapping("/delete/{id}")
    public void delete( @PathVariable String id) {
        seatBookingUsecase.deleteSeatBooking(id);
    }

    //lấy tất cả
    @RequestMapping("/getAll")
    public List<SeatBookingDTO> getAll() {
        List<SeatBooking> seatBookings = seatBookingUsecase.getAllSeatBookings();

        //chuyển từ List<SeatBooking> sang List<SeatBookingDTO>
        //dùng mapper
        List<SeatBookingDTO> seatBookingDTOs = seatBookingDTOMapper.toSeatBookingDTOs(seatBookings);

        return seatBookingDTOs;
    }

    //tìm theo id
    @RequestMapping("/find/{id}")
    public SeatBookingDTO findById(@PathVariable String id) {
        SeatBooking seatBooking = seatBookingUsecase.findSeatBookingById(id);

        //chuyển từ SeatBooking sang SeatBookingDTO
        return seatBookingDTOMapper.toSeatBookingDTO(seatBooking);
    }
}

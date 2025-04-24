package com.homeify.booking.bookingapi.Mapper;

import com.homeify.booking.Entities.SeatBooking;
import com.homeify.booking.bookingapi.DTO.SeatBooking.SeatBookingDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SeatBookingDTOMapper {

    //chuyển từ SeatBookingDTO sang SeatBooking
    SeatBooking toSeatBooking(SeatBookingDTO seatBookingDTO);

    //chuyển từ SeatBooking sang SeatBookingDTO
    SeatBookingDTO toSeatBookingDTO(SeatBooking seatBooking);

    //chuyển từ List<SeatBookingDTO> sang List<SeatBooking>
    List<SeatBooking> toSeatBookings(List<SeatBookingDTO> seatBookingDTO);

    //chuyển từ List<SeatBooking> sang List<SeatBookingDTO>
    List<SeatBookingDTO> toSeatBookingDTOs(List<SeatBooking> seatBookings);

    //chuyển từ SaveSeatBookingDTO sang SeatBooking
    SeatBooking toSeatBooking(com.homeify.booking.bookingapi.DTO.SeatBooking.SaveSeatBookingDTO saveSeatBookingDTO);
}

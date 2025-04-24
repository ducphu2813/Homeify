package com.homeify.booking.bookingapi.Mapper;

import com.homeify.booking.Entities.TripBooking;
import com.homeify.booking.bookingapi.DTO.TripBooking.SaveTripBookingDTO;
import com.homeify.booking.bookingapi.DTO.TripBooking.TripBookingDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {SeatBookingDTOMapper.class})
public interface TripBookingDTOMapper {

    //chuyển từ TripBookingDTO sang TripBooking
    TripBooking toTripBooking(TripBookingDTO tripBookingDTO);

    //chuyển từ TripBooking sang TripBookingDTO
    TripBookingDTO toTripBookingDTO(TripBooking tripBooking);

    //chuyển từ List<TripBookingDTO> sang List<TripBooking>
    List<TripBooking> toTripBookings(List<TripBookingDTO> tripBookingDTO);

    //chuyển từ List<TripBooking> sang List<TripBookingDTO>
    List<TripBookingDTO> toTripBookingDTOs(List<TripBooking> tripBooking);

    //chuyển từ SaveTripBookingDTO sang TripBooking
    TripBooking toTripBooking(SaveTripBookingDTO saveTripBookingDTO);
}

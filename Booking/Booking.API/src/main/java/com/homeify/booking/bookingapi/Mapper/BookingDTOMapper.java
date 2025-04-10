package com.homeify.booking.bookingapi.Mapper;

import com.homeify.booking.Entities.Booking;
import com.homeify.booking.bookingapi.DTO.BookingDTO;
import com.homeify.booking.bookingapi.DTO.SaveBookingDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {TripBookingDTOMapper.class})
public interface BookingDTOMapper {

    //chuyển từ BookingDTO sang Booking
    Booking toBooking(BookingDTO bookingDTO);

    //chuyển từ Booking sang BookingDTO
    BookingDTO toBookingDTO(Booking booking);

    //chuyển từ List<BookingDTO> sang List<Booking>
    List<Booking> toBookings(List<BookingDTO> bookingDTO);

    //chuyển từ List<Booking> sang List<BookingDTO>
    List<BookingDTO> toBookingDTOs(List<Booking> booking);

    //chuyển từ SaveBookingDTO sang Booking
    Booking toBooking(SaveBookingDTO saveBookingDTO);
}

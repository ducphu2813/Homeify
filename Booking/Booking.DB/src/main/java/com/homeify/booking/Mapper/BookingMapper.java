package com.homeify.booking.Mapper;

import com.homeify.booking.Entities.Booking;
import com.homeify.booking.Model.BookingModel;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(uses = {TripBookingMapper.class})
public interface BookingMapper {

    //chuyển từ BookingModel sang Booking
    Booking toBooking(BookingModel bookingModel);

    //chuyển từ Booking sang BookingModel
    BookingModel toBookingModel(Booking booking);

    //chuyển từ List<BookingModel> sang List<Booking>
    List<Booking> toBookings(List<BookingModel> bookingModels);

    //chuyển từ List<Booking> sang List<BookingModel>
    List<BookingModel> toBookingModels(List<Booking> bookings);
}

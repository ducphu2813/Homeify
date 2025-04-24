package com.homeify.booking.Mapper;

import com.homeify.booking.Entities.SeatBooking;
import com.homeify.booking.Model.SeatBookingModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface SeatBookingMapper {

    //chuyển từ SeatBookingModel sang SeatBooking
    @Mapping(target = "tripBooking", ignore = true)
    SeatBooking toSeatBooking(SeatBookingModel seatBookingModel);

    //chuyển từ SeatBooking sang SeatBookingModel
    @Mapping(target = "tripBooking", ignore = true)
    SeatBookingModel toSeatBookingModel(SeatBooking seatBooking);

    //chuyển từ List<SeatBookingModel> sang List<SeatBooking>
    @Mapping(target = "tripBooking", ignore = true)
    List<SeatBooking> toSeatBookings(List<SeatBookingModel> seatBookingModels);

    //chuyển từ List<SeatBooking> sang List<SeatBookingModel>
    @Mapping(target = "tripBooking", ignore = true)
    List<SeatBookingModel> toSeatBookingModels(List<SeatBooking> seatBookings);
}

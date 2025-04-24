package com.homeify.booking.Mapper;

import com.homeify.booking.Entities.TripBooking;
import com.homeify.booking.Model.TripBookingModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(uses = {SeatBookingMapper.class})
public interface TripBookingMapper {

    //chuyển từ TripBookingModel sang TripBooking
    @Mapping(target = "booking", ignore = true)
    TripBooking toTripBooking(TripBookingModel tripBookingModel);

    //chuyển từ TripBooking sang TripBookingModel
    @Mapping(target = "booking", ignore = true)
    TripBookingModel toTripBookingModel(TripBooking tripBooking);

    //chuyển từ List<TripBookingModel> sang List<TripBooking>
    @Mapping(target = "booking", ignore = true)
    List<TripBooking> toTripBookings(List<TripBookingModel> tripBookingModels);

    //chuyển từ List<TripBooking> sang List<TripBookingModel>
    @Mapping(target = "booking", ignore = true)
    List<TripBookingModel> toTripBookingModels(List<TripBooking> tripBookings);
}

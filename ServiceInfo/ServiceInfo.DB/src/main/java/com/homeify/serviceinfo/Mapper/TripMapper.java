package com.homeify.serviceinfo.Mapper;

import com.homeify.serviceinfo.Entities.Trip;
import com.homeify.serviceinfo.Model.TripModel;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(uses = {TripInfoMapper.class, TransportationMapper.class, PickupAreaMapper.class})
public interface TripMapper {

    //chuyển từ TripModel sang Trip
    Trip toTrip(TripModel tripModel);

    //chuyển từ Trip sang TripModel
    TripModel toTripModel(Trip trip);

    //chuyển từ List<TripModel> sang List<Trip>
    List<Trip> toTrip(List<TripModel> tripModel);

    //chuyển từ List<Trip> sang List<TripModel>
    List<TripModel> toTripModel(List<Trip> trip);
}

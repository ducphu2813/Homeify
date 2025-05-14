package com.homeify.serviceinfo.serviceinfoapi.Mapper;

import com.homeify.serviceinfo.Entities.Trip;
import com.homeify.serviceinfo.Mapper.PickupAreaMapper;
import com.homeify.serviceinfo.serviceinfoapi.DTO.Trip.SaveTripDTO;
import com.homeify.serviceinfo.serviceinfoapi.DTO.Trip.TripDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {TripInfoDTOMapper.class, TransportationDTOMapper.class, PickupAreaMapper.class})
public interface TripDTOMapper {

    //chuyển từ Trip sang TripDTO
    TripDTO toTripDTO(Trip trip);

    //chuyển từ TripDTO sang Trip
    Trip toTrip(TripDTO tripDTO);

    //chuyển từ List<Trip> sang List<TripDTO>
    List<TripDTO> toTripDTOs(List<Trip> trips);

    //chuyển từ List<TripDTO> sang List<Trip>
    List<Trip> toTrips(List<TripDTO> tripDTOs);

    //chuyển từ SaveTripDTO sang Trip
    Trip toTrip(SaveTripDTO saveTripDTO);
}

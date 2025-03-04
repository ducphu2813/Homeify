package com.homeify.serviceinfo.serviceinfoapi.Controller;

import com.homeify.serviceinfo.Entities.*;
import com.homeify.serviceinfo.UseCases.TripUsecase;
import com.homeify.serviceinfo.serviceinfoapi.DTO.Trip.SaveTripDTO;
import com.homeify.serviceinfo.serviceinfoapi.DTO.Trip.TripDTO;
import com.homeify.serviceinfo.serviceinfoapi.Mapper.TripDTOMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trip")
public class TripController {

    private final TripUsecase tripUsecase;
    private final TripDTOMapper tripDTOMapper;

    public TripController(TripUsecase tripUsecase, TripDTOMapper tripDTOMapper) {
        this.tripUsecase = tripUsecase;
        this.tripDTOMapper = tripDTOMapper;
    }

    //get all
    @GetMapping("/getAll")
    public List<TripDTO> getAll() {

        List<Trip> trips = tripUsecase.getAllTrip();

        //chuyển từ List<Trip> sang List<TripDTO>
        //dùng mapper
        return tripDTOMapper.toTripDTOs(trips);

    }

    //thêm
    @PostMapping("/add")
    public TripDTO add(@RequestBody SaveTripDTO tripDTO) {

        Trip trip = tripDTOMapper.toTrip(tripDTO);

        //lấy id từ dto rồi set vào entity
        TripInfo tripInfo = new TripInfo();
        tripInfo.setId(tripDTO.getTripInfoId());

        Transportation transportation = new Transportation();
        transportation.setId(tripDTO.getTransportationId());

        PickupArea pickupArea = new PickupArea();
        pickupArea.setId(tripDTO.getPickupAreaId());

        trip.setTripInfo(tripInfo);
        trip.setTransportation(transportation);
        trip.setPickupArea(pickupArea);

        // thao tác db
        trip = tripUsecase.addTrip(trip);

        return tripDTOMapper.toTripDTO(trip);

    }

    //sửa
    @PutMapping("/update/{id}")
    public TripDTO update(@RequestBody SaveTripDTO tripDTO, @PathVariable String id) {

        Trip trip = tripDTOMapper.toTrip(tripDTO);

        //lấy id từ dto rồi set vào entity
        TripInfo tripInfo = new TripInfo();
        tripInfo.setId(tripDTO.getTripInfoId());

        Transportation transportation = new Transportation();
        transportation.setId(tripDTO.getTransportationId());

        PickupArea pickupArea = new PickupArea();
        pickupArea.setId(tripDTO.getPickupAreaId());

        trip.setTripInfo(tripInfo);
        trip.setTransportation(transportation);
        trip.setPickupArea(pickupArea);

        //thao tác db
        trip = tripUsecase.updateTrip(trip, id);

        return tripDTOMapper.toTripDTO(trip);

    }

    //xóa
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable String id) {
        tripUsecase.deleteTrip(id);
    }

    //get by id
    @GetMapping("/get/{id}")
    public TripDTO getById(@PathVariable String id) {
        Trip trip = tripUsecase.findTripById(id);
        return tripDTOMapper.toTripDTO(trip);
    }
}

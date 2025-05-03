package com.homeify.serviceinfo.serviceinfoapi.Controller;

import com.homeify.serviceinfo.Entities.*;
import com.homeify.serviceinfo.UseCases.TripUsecase;
import com.homeify.serviceinfo.serviceinfoapi.DTO.Trip.SaveTripDTO;
import com.homeify.serviceinfo.serviceinfoapi.DTO.Trip.TripDTO;
import com.homeify.serviceinfo.serviceinfoapi.FeignClient.BookingClientInterface;
import com.homeify.serviceinfo.serviceinfoapi.Mapper.TripDTOMapper;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/trip")
public class TripController {

    private final TripUsecase tripUsecase;
    private final TripDTOMapper tripDTOMapper;

    //inject feign client của booking
    private final BookingClientInterface bookingFeignClient;

    public TripController(TripUsecase tripUsecase
                            , TripDTOMapper tripDTOMapper
                                , BookingClientInterface bookingFeignClient) {
        this.tripUsecase = tripUsecase;
        this.tripDTOMapper = tripDTOMapper;
        this.bookingFeignClient = bookingFeignClient;
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

        if(trip == null) {
            throw new RuntimeException("Error while adding trip");
        }

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
    public Map<String, Object> getById(@PathVariable String id) {
        Trip trip = tripUsecase.findTripById(id);

        List<String> bookedSeats = bookingFeignClient.findBookedSeatIdsByTripId(id);

        TripDTO tripDTO = tripDTOMapper.toTripDTO(trip);

        //trả về 1 map có tripDTO và bookedSeats
        Map<String, Object> response = new HashMap<>();
        response.put("trip", tripDTO);
        response.put("bookedSeats", bookedSeats);

        return response;
    }

    //tìm theo departureCityId và arrivalCityId và >= departureDate
    @GetMapping("/search")
    public List<TripDTO> search(@RequestParam String departureCityId,
                                @RequestParam String arrivalCityId,
                                @RequestParam LocalDateTime departureAfter) {

        List<Trip> trips = tripUsecase.findByDepartureCityIdAndArrivalCityIdAndDepartureDateGreaterThanEqual(departureCityId, arrivalCityId, departureAfter);

        //chuyển từ List<Trip> sang List<TripDTO>
        //dùng mapper
        return tripDTOMapper.toTripDTOs(trips);

    }
}

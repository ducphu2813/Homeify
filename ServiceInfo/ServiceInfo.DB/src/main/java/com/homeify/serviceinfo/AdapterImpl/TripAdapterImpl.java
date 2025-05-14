package com.homeify.serviceinfo.AdapterImpl;

import com.homeify.serviceinfo.Entities.*;
import com.homeify.serviceinfo.Mapper.*;
import com.homeify.serviceinfo.Model.*;
import com.homeify.serviceinfo.Repository.*;
import org.springframework.stereotype.Service;
import com.homeify.serviceinfo.Adapter.TripAdapter;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
public class TripAdapterImpl implements TripAdapter {

    private final TripRepository tripRepository;
    private final TripMapper tripMapper;

    private final TripInfoRepository tripInfoRepository;

    public TripAdapterImpl(TripRepository tripRepository
                            , TripMapper tripMapper
                            , TripInfoRepository tripInfoRepository
    ) {
        this.tripRepository = tripRepository;
        this.tripMapper = tripMapper;
        this.tripInfoRepository = tripInfoRepository;
    }

    @Override
    @Transactional
    public Trip addTrip(Trip trip) {
        //dùng mapper để chuyển từ entity sang model
        TripModel tripModel = tripMapper.toTripModel(trip);

        //log thông tin của tripModel ra
        System.out.println("id của transportation: " + tripModel.getTransportation().getId());

        //kiểm tra tồn tại
        if(tripRepository.existsByTripInfoIdAndDepartureTime(trip.getTripInfo().getId(), trip.getDepartureTime())) {
            return null;
        }

        //lấy các chuyến trùng lịch
        List<TripModel> busyTrips = tripRepository.findBusyTrips(trip.getDepartureTime(), trip.getArrivalTime());

        //lấy id của các xe trùng lịch, lấy từ busyTrips, trong đó có id của transportation
        List<String> transportationIds = busyTrips.stream()
                .map(tripM -> tripM.getTransportation().getId())
                .toList();

        //kiểm tra xem id của xe trong tripModel có nằm trong danh sách xe trùng lịch không
        if(transportationIds.contains(trip.getTransportation().getId())) {
            return null;
        }

        //tự động tạo id
        tripModel.setId(generateId());

        //lưu vào db
        tripModel = tripRepository.save(tripModel);

        return tripMapper.toTrip(tripModel);
    }

    @Override
    @Transactional
    public Trip updateTrip(Trip trip, String tripId) {
        //tìm theo id
        TripModel tripModel = tripRepository.findById(tripId).orElse(null);

        if(tripModel == null) {
            return null;
        }

        //dùng mapper
        tripModel = tripMapper.toTripModel(trip);

        //set id cũ cho trip
        tripModel.setId(tripId);

        tripModel = tripRepository.save(tripModel);

        return tripMapper.toTrip(tripModel);
    }

    @Override
    @Transactional
    public void deleteTrip(String tripId) {
        tripRepository.deleteById(tripId);
    }

    @Override
    public List<Trip> getAllTrip() {

        List<TripModel> tripModelList = tripRepository.findAll();

        //dùng mapper
        return tripMapper.toTrip(tripModelList);
    }

    @Override
    public Trip findTripById(String tripId) {

        TripModel tripModel = tripRepository.findById(tripId).orElse(null);

        if(tripModel == null) {
            return null;
        }

        //dùng mapper
        return tripMapper.toTrip(tripModel);

    }


    //tìm theo departureCityId và arrivalCityId và >= departureDate
    @Override
    public List<Trip> findByDepartureCityIdAndArrivalCityIdAndDepartureDateGreaterThanEqual(String departureCityId, String arrivalCityId, LocalDateTime departureDate) {

        List<TripInfoModel> tripInfoModelList = tripInfoRepository.findByDepartureCityIdAndArrivalCityId(departureCityId, arrivalCityId);

        //lấy ra danh sách id của tripInfoModelList
        List<String> tripInfoIdList = tripInfoModelList.stream()
                .map(TripInfoModel::getId)
                .filter(Objects::nonNull)
                .distinct()
                .toList();


        List<TripModel> tripModelList = tripRepository.findByDepartureTimeGreaterThanEqualAndTripInfoIdIn(tripInfoIdList, departureDate);

        //dùng mapper
        return tripMapper.toTrip(tripModelList);
    }


    //cập nhật số ghế trống cho trip theo trip id, nhận vào trip id và số ghế trống và action "delete" hoặc "book"
    @Override
    @Transactional
    public void updateAvailableSeats(String tripId, int seatCount, String action) {
        //kiểm tra action
        if(!action.equals("delete") && !action.equals("book")) {
            throw new IllegalArgumentException("Action must be delete or book");
        }

        //cập nhật số ghế trống
        tripRepository.updateAvailableSeats(tripId, seatCount, action);
    }




    //tự động tạo id(TR00001, TR00002,...)
    private String generateId() {
        return "TR" + String.format("%05d", tripRepository.count() + 1);
    }


}

package com.homeify.serviceinfo.AdapterImpl;

import com.homeify.serviceinfo.Entities.PickupArea;
import com.homeify.serviceinfo.Entities.Transportation;
import com.homeify.serviceinfo.Entities.TripInfo;
import com.homeify.serviceinfo.Mapper.*;
import com.homeify.serviceinfo.Model.*;
import com.homeify.serviceinfo.Repository.*;
import org.springframework.stereotype.Service;
import com.homeify.serviceinfo.Entities.Trip;
import com.homeify.serviceinfo.Adapter.TripAdapter;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class TripAdapterImpl implements TripAdapter {

    private final TripRepository tripRepository;
    private final TripMapper tripMapper;

    private final TripInfoRepository tripInfoRepository;
    private final TripInfoMapper tripInfoMapper;

    private final TransportationRepository transportationRepository;
    private final TransportationMapper transportationMapper;

    private final PickupAreaRepository pickupAreaRepository;
    private final PickupAreaMapper pickupAreaMapper;

    private final CityRepository cityRepository;
    private final CityMapper cityMapper;

    public TripAdapterImpl(TripRepository tripRepository
                            , TripMapper tripMapper
                            , TripInfoRepository tripInfoRepository
                            , TripInfoMapper tripInfoMapper
                            , TransportationRepository transportationRepository
                            , TransportationMapper transportationMapper
                            , PickupAreaRepository pickupAreaRepository
                            , PickupAreaMapper pickupAreaMapper
                            , CityRepository cityRepository
                            , CityMapper cityMapper) {
        this.tripRepository = tripRepository;
        this.tripMapper = tripMapper;
        this.tripInfoRepository = tripInfoRepository;
        this.tripInfoMapper = tripInfoMapper;
        this.transportationRepository = transportationRepository;
        this.transportationMapper = transportationMapper;
        this.pickupAreaRepository = pickupAreaRepository;
        this.pickupAreaMapper = pickupAreaMapper;
        this.cityRepository = cityRepository;
        this.cityMapper = cityMapper;
    }

    @Override
    @Transactional
    public Trip addTrip(Trip trip) {
        //dùng mapper để chuyển từ model sang entity
        TripModel tripModel = tripMapper.toTripModel(trip);

        //tự động tạo id
        tripModel.setId(generateId());

        //set id cho trip info
        tripModel.setTripInfoId(trip.getTripInfo().getId());

        //set id cho transportation
        tripModel.setTransportationId(trip.getTransportation().getId());

        //set id cho pickup area
        tripModel.setPickupAreaId(trip.getPickupArea().getId());

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

        //set id cho trip info
        tripModel.setTripInfoId(trip.getTripInfo().getId());

        //set id cho transportation
        tripModel.setTransportationId(trip.getTransportation().getId());

        //set id cho pickup area
        tripModel.setPickupAreaId(trip.getPickupArea().getId());

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

        //lấy tất cả tripInfoId từ tripModelList
        List<String> tripInfoIdList = tripModelList.stream()
                .map(TripModel::getTripInfoId)
                .filter(Objects::nonNull)
                .distinct()
                .toList();

        //lấy tất cả transportationId từ tripModelList
        List<String> transportationIdList = tripModelList.stream()
                .map(TripModel::getTransportationId)
                .filter(Objects::nonNull)
                .distinct()
                .toList();

        //lấy tất cả pickupAreaId từ tripModelList
        List<String> pickupAreaIdList = tripModelList.stream()
                .map(TripModel::getPickupAreaId)
                .filter(Objects::nonNull)
                .distinct()
                .toList();

        //lấy tất cả tripInfoModel từ tripInfoIdList
        List<TripInfoModel> tripInfoModelList = tripInfoRepository.findAllById(tripInfoIdList);

        //lấy tất cả arrivalCityId và departureCityId
        List<String> cityIds = tripInfoModelList.stream()
                .flatMap(tripInfoModel -> Stream.of(
                        tripInfoModel.getArrivalCityId(),
                        tripInfoModel.getDepartureCityId()
                ))
                .filter(Objects::nonNull)
                .distinct()
                .toList();

        //lấy tất cả CityModel tương ứng
        List<CityModel> cityModels = cityRepository.findAllByIdIn(cityIds);

        //trả về 1 Map<String, TripInfo> với key là id của tripInfo
        //và value là tripInfo tương ứng
        Map<String, TripInfo> tripInfoMap = tripInfoModelList.stream()
                .collect(Collectors.toMap(TripInfoModel::getId, tripInfoModel -> {
                    TripInfo tripInfo = tripInfoMapper.toTripInfo(tripInfoModel);

                    //set arrivalCity và departureCity
                    tripInfo.setArrivalCity(cityMapper.toCity(cityModels.stream()
                            .filter(cityModel -> cityModel.getId().equals(tripInfoModel.getArrivalCityId()))
                            .findFirst()
                            .orElse(null)));

                    tripInfo.setDepartureCity(cityMapper.toCity(cityModels.stream()
                            .filter(cityModel -> cityModel.getId().equals(tripInfoModel.getDepartureCityId()))
                            .findFirst()
                            .orElse(null)));

                    return tripInfo;
                }));

        //làm 1 Map<String, Transportation> tương tự như trên
        Map<String, Transportation> transportationMap = transportationRepository.findAllById(transportationIdList)
                .stream()
                .map(transportationMapper::toTransportation)
                .collect(Collectors.toMap(Transportation::getId, Function.identity()));

        //làm 1 Map<String, PickupArea> tương tự như trên
        Map<String, PickupArea> pickupAreaMap = pickupAreaRepository.findAllById(pickupAreaIdList)
                .stream()
                .map(pickupAreaMapper::toPickupArea)
                .collect(Collectors.toMap(PickupArea::getId, Function.identity()));

        //trả về danh sách trip với tripInfo, transportation, pickupArea đã được set
        return tripModelList.stream()
                .map(tripModel -> {
                    Trip trip = tripMapper.toTrip(tripModel);
                    trip.setTripInfo(tripInfoMap.get(tripModel.getTripInfoId()));
                    trip.setTransportation(transportationMap.get(tripModel.getTransportationId()));
                    trip.setPickupArea(pickupAreaMap.get(tripModel.getPickupAreaId()));
                    return trip;
                })
                .toList();

    }

    @Override
    public Trip findTripById(String tripId) {

        TripModel tripModel = tripRepository.findById(tripId).orElse(null);

        if(tripModel == null) {
            return null;
        }

        //lấy các Model của tripInfo, transportation, pickupArea
        TripInfoModel tripInfoModel = tripInfoRepository.findById(tripModel.getTripInfoId()).orElse(null);
        TransportationModel transportationModel = transportationRepository.findById(tripModel.getTransportationId()).orElse(null);
        PickupAreaModel pickupAreaModel = pickupAreaRepository.findById(tripModel.getPickupAreaId()).orElse(null);

        //lấy Model của departureCity và arrivalCity
        CityModel departureCity = cityRepository.findById(tripInfoModel.getDepartureCityId()).orElse(null);
        CityModel arrivalCity = cityRepository.findById(tripInfoModel.getArrivalCityId()).orElse(null);

        //dùng mapper chuyển TripInfoModel sang TripInfo
        TripInfo tripInfo = tripInfoMapper.toTripInfo(tripInfoModel);
        tripInfo.setDepartureCity(cityMapper.toCity(departureCity));
        tripInfo.setArrivalCity(cityMapper.toCity(arrivalCity));

        //làm tương tự cho transportation và pickupArea
        Transportation transportation = transportationMapper.toTransportation(transportationModel);
        PickupArea pickupArea = pickupAreaMapper.toPickupArea(pickupAreaModel);

        //hoàn thiện trip
        Trip trip = tripMapper.toTrip(tripModel);
        trip.setTripInfo(tripInfo);
        trip.setTransportation(transportation);
        trip.setPickupArea(pickupArea);

        return trip;

    }

    //tự động tạo id(TR00001, TR00002,...)
    private String generateId() {
        return "TR" + String.format("%05d", tripRepository.count() + 1);
    }


}

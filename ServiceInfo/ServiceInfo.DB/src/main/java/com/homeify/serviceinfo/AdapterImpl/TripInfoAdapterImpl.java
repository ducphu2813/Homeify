package com.homeify.serviceinfo.AdapterImpl;

import com.homeify.serviceinfo.Adapter.TripInfoAdapter;
import com.homeify.serviceinfo.Entities.TripInfo;
import com.homeify.serviceinfo.Mapper.CityMapper;
import com.homeify.serviceinfo.Mapper.TripInfoMapper;
import com.homeify.serviceinfo.Model.CityModel;
import com.homeify.serviceinfo.Model.TripInfoModel;
import com.homeify.serviceinfo.Repository.CityRepository;
import com.homeify.serviceinfo.Repository.TripInfoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class TripInfoAdapterImpl implements TripInfoAdapter {

    private final TripInfoRepository tripInfoRepository;
    private final TripInfoMapper tripInfoMapper;

    private final CityRepository cityRepository;
    private final CityMapper cityMapper;

    public TripInfoAdapterImpl(TripInfoRepository tripInfoRepository
                                , TripInfoMapper tripInfoMapper
                                , CityRepository cityRepository
                                , CityMapper cityMapper) {
        this.tripInfoRepository = tripInfoRepository;
        this.tripInfoMapper = tripInfoMapper;
        this.cityRepository = cityRepository;
        this.cityMapper = cityMapper;
    }

    @Override
    @Transactional
    public TripInfo addTripInfo(TripInfo tripInfo) {
        //chuyyển từ TripInfo sang TripInfoModel
        TripInfoModel tripInfoModel = tripInfoMapper.toTripInfoModel(tripInfo);

        //set id
        tripInfoModel.setId(generateId());

        //set id của city
        tripInfoModel.setArrivalCityId(tripInfo.getArrivalCity().getId());
        tripInfoModel.setDepartureCityId(tripInfo.getDepartureCity().getId());

        //lưu vào db
        tripInfoRepository.save(tripInfoModel);

        //chuyển từ TripInfoModel sang TripInfo
        return tripInfoMapper.toTripInfo(tripInfoModel);
    }

    @Override
    @Transactional
    public TripInfo updateTripInfo(TripInfo tripInfo, String tripInfoId) {
        //tìm theo id
        TripInfoModel tripInfoModel = tripInfoRepository.findById(tripInfoId).orElse(null);

        if(tripInfoModel == null) {
            return null;
        }

        //chuyển từ TripInfo sang TripInfoModel
        tripInfoModel = tripInfoMapper.toTripInfoModel(tripInfo);

        //set id cũ của tripInfo
        tripInfoModel.setId(tripInfoId);

        //set id của city
        tripInfoModel.setArrivalCityId(tripInfo.getArrivalCity().getId());
        tripInfoModel.setDepartureCityId(tripInfo.getDepartureCity().getId());

        //lưu vào db
        tripInfoRepository.save(tripInfoModel);

        //chuyển từ TripInfoModel sang TripInfo
        return tripInfoMapper.toTripInfo(tripInfoModel);
    }

    @Override
    @Transactional
    public void deleteTripInfo(String tripInfoId) {
        //xóa theo id
        tripInfoRepository.deleteById(tripInfoId);
    }

    @Override
    public List<TripInfo> getAllTripInfo() {
        List<TripInfoModel> tripInfoModelList = tripInfoRepository.findAll();

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

        //chuyển từ TripInfoModel sang TripInfo
        return tripInfoModelList.stream()
                .map(tripInfoModel -> {
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
                })
                .toList();
    }

    @Override
    public TripInfo findTripInfoById(String tripInfoId) {
        //tìm theo id
        TripInfoModel tripInfoModel = tripInfoRepository.findById(tripInfoId).orElse(null);

        if(tripInfoModel == null) {
            return null;
        }

        //lấy tất cả cityId
        List<String> cityIds = List.of(tripInfoModel.getArrivalCityId(), tripInfoModel.getDepartureCityId());

        //lấy tất cả CityModel tương ứng
        List<CityModel> cityModels = cityRepository.findAllByIdIn(cityIds);

        //chuyển từ TripInfoModel sang TripInfo
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
    }

    //tự động tạo id(CH0001, CH0002, ...)
    private String generateId() {
        TripInfoModel tripInfoModel = tripInfoRepository.findTopByOrderByIdDesc();
        if (tripInfoModel == null) {
            return "CH0001";
        }
        String id = tripInfoModel.getId();
        String prefix = id.substring(0, 2);
        String suffix = id.substring(2);
        int number = Integer.parseInt(suffix);
        number++;
        return prefix + String.format("%04d", number);
    }
}

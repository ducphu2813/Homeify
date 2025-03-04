package com.homeify.serviceinfo.AdapterImpl;

import com.homeify.serviceinfo.Adapter.PickupAreaAdapter;
import com.homeify.serviceinfo.Entities.City;
import com.homeify.serviceinfo.Entities.PickupArea;
import com.homeify.serviceinfo.Mapper.CityMapper;
import com.homeify.serviceinfo.Mapper.PickupAreaMapper;
import com.homeify.serviceinfo.Model.CityModel;
import com.homeify.serviceinfo.Model.PickupAreaModel;
import com.homeify.serviceinfo.Repository.CityRepository;
import com.homeify.serviceinfo.Repository.PickupAreaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
public class PickupAreaAdapterImpl implements PickupAreaAdapter {

    private final PickupAreaRepository pickupAreaRepository;
    private final PickupAreaMapper pickupAreaMapper;

    private final CityRepository cityRepository;
    private final CityMapper cityMapper;

    public PickupAreaAdapterImpl(PickupAreaRepository pickupAreaRepository
                                , PickupAreaMapper pickupAreaMapper
                                , CityRepository cityRepository
                                , CityMapper cityMapper) {
        this.pickupAreaRepository = pickupAreaRepository;
        this.pickupAreaMapper = pickupAreaMapper;
        this.cityRepository = cityRepository;
        this.cityMapper = cityMapper;
    }


    @Override
    @Transactional
    public PickupArea addPickupArea(PickupArea pickupArea) {
        //chuyển từ PickupArea sang PickupAreaModel
        PickupAreaModel pickupAreaModel = pickupAreaMapper.toPickupAreaModel(pickupArea);

        //set id
        pickupAreaModel.setId(generateId());

        //set id của city
        pickupAreaModel.setCityId(pickupArea.getCity().getId());

        //lưu vào db
        pickupAreaRepository.save(pickupAreaModel);

        //chuyển từ PickupAreaModel sang PickupArea
        return pickupAreaMapper.toPickupArea(pickupAreaModel);
    }

    @Override
    @Transactional
    public PickupArea updatePickupArea(PickupArea pickupArea, String pickupAreaId) {
        //chuyển từ PickupArea sang PickupAreaModel
        PickupAreaModel pickupAreaModel = pickupAreaMapper.toPickupAreaModel(pickupArea);

        //set id
        pickupAreaModel.setId(pickupAreaId);

        //set id của city
        pickupAreaModel.setCityId(pickupArea.getCity().getId());

        //lưu vào db
        pickupAreaRepository.save(pickupAreaModel);

        //chuyển từ PickupAreaModel sang PickupArea
        return pickupAreaMapper.toPickupArea(pickupAreaModel);
    }

    @Override
    @Transactional
    public void deletePickupArea(String pickupAreaId) {
        pickupAreaRepository.deleteById(pickupAreaId);
    }

    @Override
    public List<PickupArea> getAllPickupArea() {
        //tìm theo id
        List<PickupAreaModel> pickupAreaModels = pickupAreaRepository.findAll();

        //lấy tất cả cityId từ pickupAreaModels
        List<String> cityIds = pickupAreaModels.stream()
                .map(PickupAreaModel::getCityId)
                .filter(Objects::nonNull)
                .distinct()
                .toList();

        //lấy tất cả city từ cityIds
        List<City> cities = cityRepository.findAllById(cityIds)
                .stream()
                .map(cityMapper::toCity)
                .toList();

        //chuyển từ PickupAreaModel sang PickupArea
        return pickupAreaModels.stream()
                .map(pickupAreaModel -> {
                    PickupArea pickupArea = pickupAreaMapper.toPickupArea(pickupAreaModel);

                    //set city
                    pickupArea.setCity(cities.stream()
                            .filter(city -> city.getId().equals(pickupAreaModel.getCityId()))
                            .findFirst()
                            .orElse(null));

                    return pickupArea;
                })
                .toList();
    }

    @Override
    public PickupArea findPickupAreaById(String pickupAreaId) {
        //tìm theo id
        PickupAreaModel pickupAreaModel = pickupAreaRepository.findById(pickupAreaId).orElse(null);

        if(pickupAreaModel == null) {
            return null;
        }

        //lấy city
        CityModel cityModel = cityRepository.findById(pickupAreaModel.getCityId()).orElse(null);

        //chuyển từ PickupAreaModel sang PickupArea
        PickupArea pickupArea = pickupAreaMapper.toPickupArea(pickupAreaModel);

        //set city
        pickupArea.setCity(cityMapper.toCity(cityModel));

        return pickupArea;
    }

    //tự động tạo id(PA001, PA002, ...)
    private String generateId() {

        PickupAreaModel pickupAreaModel = pickupAreaRepository.findTopByOrderByIdDesc();
        if(pickupAreaModel == null) {
            return "PA001";
        }
        String id = pickupAreaModel.getId();
        String prefix = id.substring(0, 2);
        String suffix = id.substring(2);
        int number = Integer.parseInt(suffix);
        number++;
        return prefix + String.format("%03d", number);
    }
}

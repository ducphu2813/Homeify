package com.homeify.serviceinfo.AdapterImpl;

import com.homeify.serviceinfo.Adapter.CityAdapter;
import com.homeify.serviceinfo.Entities.City;
import com.homeify.serviceinfo.Mapper.CityMapper;
import com.homeify.serviceinfo.Model.CityModel;
import com.homeify.serviceinfo.Repository.CityRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CityAdapterImpl implements CityAdapter {

    private final CityRepository cityRepository;
    private final CityMapper cityMapper;

    public CityAdapterImpl(CityRepository cityRepository
                            , CityMapper cityMapper) {
        this.cityRepository = cityRepository;
        this.cityMapper = cityMapper;
    }

    @Override
    @Transactional
    public City addCity(City city) {
        CityModel cityModel = cityMapper.toCityModel(city);

        //tự động tạo id
        cityModel.setId(generateId());

        cityModel = cityRepository.save(cityModel);

        return cityMapper.toCity(cityModel);
    }

    @Override
    @Transactional
    public City updateCity(City city, String cityId) {
        //tìm theo id
        CityModel cityModel = cityRepository.findById(cityId).orElse(null);

        if (cityModel == null) {
            return null;
        }

        //dùng mapper
        cityModel = cityMapper.toCityModel(city);

        //set id cũ
        cityModel.setId(cityId);

        cityModel = cityRepository.save(cityModel);

        return cityMapper.toCity(cityModel);
    }

    @Override
    @Transactional
    public void deleteCity(String cityId) {
        cityRepository.deleteById(cityId);
    }

    @Override
    public List<City> getAllCity() {
        List<CityModel> cityModels = cityRepository.findAll();

        return cityMapper.toCity(cityModels);
    }

    @Override
    public City findCityById(String cityId) {
        CityModel cityModel = cityRepository.findById(cityId).orElse(null);

        return cityMapper.toCity(cityModel);
    }

    //tự động tạo id(CT001, CT002, ...)
    private String generateId() {
        //lấy id cuối cùng
        CityModel cityModel = cityRepository.findTopByOrderByIdDesc();

        if (cityModel == null) {
            return "CT001";
        }

        String id = cityModel.getId();
        String prefix = id.substring(0, 2);
        String number = id.substring(2);

        int num = Integer.parseInt(number);
        num++;

        return prefix + String.format("%03d", num);
    }
}

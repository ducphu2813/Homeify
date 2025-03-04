package com.homeify.serviceinfo.Mapper;

import com.homeify.serviceinfo.Entities.City;
import com.homeify.serviceinfo.Model.CityModel;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface CityMapper {

    //chuyển từ CityModel sang City
    City toCity(CityModel cityModel);

    //chuyển từ City sang CityModel
    CityModel toCityModel(City city);

    //chuyển từ List<CityModel> sang List<City>
    List<City> toCity(List<CityModel> cityModel);

    //chuyển từ List<City> sang List<CityModel>
    List<CityModel> toCityModel(List<City> city);
}

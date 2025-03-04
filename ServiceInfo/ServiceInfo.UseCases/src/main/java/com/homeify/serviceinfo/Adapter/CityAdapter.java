package com.homeify.serviceinfo.Adapter;

import com.homeify.serviceinfo.Entities.City;

import java.util.List;

public interface CityAdapter {

    //thêm city
    City addCity(City city);

    //sửa city
    City updateCity(City city, String cityId);

    //xóa city
    void deleteCity(String cityId);

    //lấy tat ca city
    List<City> getAllCity();

    //tìm theo id
    City findCityById(String cityId);
}

package com.homeify.serviceinfo.serviceinfoapi.Mapper;

import com.homeify.serviceinfo.Entities.City;
import com.homeify.serviceinfo.serviceinfoapi.DTO.CityDTO;
import com.homeify.serviceinfo.serviceinfoapi.DTO.SaveCityDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CityDTOMapper {

    //chuyển từ City sang CityDTO
    CityDTO toCityDTO(City city);

    //chuyển từ CityDTO sang City
    City toCity(CityDTO cityDTO);

    //chuyển từ List<City> sang List<CityDTO>
    List<CityDTO> toCityDTOs(List<City> cities);

    //chuyển từ List<CityDTO> sang List<City>
    List<City> toCities(List<CityDTO> cityDTOs);

    //chuyển từ City sang SaveCityDTO
    SaveCityDTO toSaveCityDTO(City city);

    //chuyển từ SaveCityDTO sang City
    City toCity(SaveCityDTO saveCityDTO);
}

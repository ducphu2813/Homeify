package com.homeify.serviceinfo.serviceinfoapi.Controller;

import com.homeify.serviceinfo.Entities.City;
import com.homeify.serviceinfo.UseCases.CityUsecase;
import com.homeify.serviceinfo.serviceinfoapi.DTO.CityDTO;
import com.homeify.serviceinfo.serviceinfoapi.DTO.SaveCityDTO;
import com.homeify.serviceinfo.serviceinfoapi.Mapper.CityDTOMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/city")
public class CityController {

    private final CityUsecase cityUsecase;
    private final CityDTOMapper cityDTOMapper;

    public CityController(CityUsecase cityUsecase
                            , CityDTOMapper cityDTOMapper) {
        this.cityUsecase = cityUsecase;
        this.cityDTOMapper = cityDTOMapper;
    }

    //get all
    @GetMapping("/getAll")
    public List<CityDTO> getAll() {

        List<City> cities = cityUsecase.getAllCity();

        //chuyển từ List<City> sang List<CityDTO>
        //dùng mapper
        return cityDTOMapper.toCityDTOs(cities);

    }

    //thêm
    @PostMapping("/add")
    public CityDTO add(@RequestBody SaveCityDTO cityDTO) {

        City city = cityDTOMapper.toCity(cityDTO);

        city = cityUsecase.addCity(city);

        return cityDTOMapper.toCityDTO(city);

    }

    //sửa
    @PutMapping("/update/{id}")
    public CityDTO update(@RequestBody SaveCityDTO cityDTO, @PathVariable String id) {

        City city = cityDTOMapper.toCity(cityDTO);

        city = cityUsecase.updateCity(city, id);

        return cityDTOMapper.toCityDTO(city);

    }

    //xóa
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable String id) {

        cityUsecase.deleteCity(id);

    }
}

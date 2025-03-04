package com.homeify.serviceinfo.serviceinfoapi.Controller;

import com.homeify.serviceinfo.Entities.City;
import com.homeify.serviceinfo.Entities.TripInfo;
import com.homeify.serviceinfo.UseCases.TripInfoUsecase;
import com.homeify.serviceinfo.serviceinfoapi.DTO.TripInfo.SaveTripInfoDTO;
import com.homeify.serviceinfo.serviceinfoapi.DTO.TripInfo.TripInfoDTO;
import com.homeify.serviceinfo.serviceinfoapi.Mapper.TripInfoDTOMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trip-info")
public class TripInfoController {

    private final TripInfoUsecase tripInfoUsecase;
    private final TripInfoDTOMapper tripInfoDTOMapper;

    public TripInfoController(TripInfoUsecase tripInfoUsecase
                                , TripInfoDTOMapper tripInfoDTOMapper) {
        this.tripInfoUsecase = tripInfoUsecase;
        this.tripInfoDTOMapper = tripInfoDTOMapper;
    }

    //get all
    @GetMapping("/getAll")
    public List<TripInfoDTO> getAll() {

        List<TripInfo> tripInfos = tripInfoUsecase.getAllTripInfo();

        //chuyển từ List<TripInfo> sang List<TripInfoDTO>
        //dùng mapper
        return tripInfoDTOMapper.toTripInfoDTOs(tripInfos);

    }

    //thêm
    @PostMapping("/add")
    public TripInfoDTO add(@RequestBody SaveTripInfoDTO tripInfoDTO) {

        TripInfo tripInfo = tripInfoDTOMapper.toTripInfo(tripInfoDTO);

        //lấy id từ dto rồi set vào entity
        City departureCity = new City();
        departureCity.setId(tripInfoDTO.getDepartureCityId());

        City arrivalCity = new City();
        arrivalCity.setId(tripInfoDTO.getArrivalCityId());

        tripInfo.setDepartureCity(departureCity);
        tripInfo.setArrivalCity(arrivalCity);

        tripInfo = tripInfoUsecase.addTripInfo(tripInfo);

        return tripInfoDTOMapper.toTripInfoDTO(tripInfo);

    }

    //sửa
    @PutMapping("/update/{id}")
    public TripInfoDTO update(@RequestBody SaveTripInfoDTO tripInfoDTO, @PathVariable String id) {

        TripInfo tripInfo = tripInfoDTOMapper.toTripInfo(tripInfoDTO);

        //lấy id từ dto rồi set vào entity
        City departureCity = new City();
        departureCity.setId(tripInfoDTO.getDepartureCityId());

        City arrivalCity = new City();
        arrivalCity.setId(tripInfoDTO.getArrivalCityId());

        tripInfo.setDepartureCity(departureCity);
        tripInfo.setArrivalCity(arrivalCity);

        tripInfo = tripInfoUsecase.updateTripInfo(tripInfo, id);

        return tripInfoDTOMapper.toTripInfoDTO(tripInfo);

    }

    //xóa
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable String id) {

        tripInfoUsecase.deleteTripInfo(id);

    }

    //tìm theo id
    @GetMapping("/find/{id}")
    public TripInfoDTO find(@PathVariable String id) {

        TripInfo tripInfo = tripInfoUsecase.findTripInfoById(id);

        return tripInfoDTOMapper.toTripInfoDTO(tripInfo);

    }
}

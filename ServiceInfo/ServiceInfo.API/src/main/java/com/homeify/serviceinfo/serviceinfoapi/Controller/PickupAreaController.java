package com.homeify.serviceinfo.serviceinfoapi.Controller;


import com.homeify.serviceinfo.Entities.City;
import com.homeify.serviceinfo.Entities.PickupArea;
import com.homeify.serviceinfo.UseCases.PickupAreaUsecase;
import com.homeify.serviceinfo.serviceinfoapi.DTO.PickupArea.PickupAreaDTO;
import com.homeify.serviceinfo.serviceinfoapi.DTO.PickupArea.SavePickupAreaDTO;
import com.homeify.serviceinfo.serviceinfoapi.Mapper.PickupAreaDTOMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pickuparea")
public class PickupAreaController {

    private final PickupAreaUsecase pickupAreaUsecase;
    private final PickupAreaDTOMapper pickupAreaDTOMapper;

    public PickupAreaController(PickupAreaUsecase pickupAreaUsecase
                                    , PickupAreaDTOMapper pickupAreaDTOMapper) {
        this.pickupAreaUsecase = pickupAreaUsecase;
        this.pickupAreaDTOMapper = pickupAreaDTOMapper;
    }

    //get all
    @GetMapping("/getAll")
    public List<PickupAreaDTO> getAll() {

        List<PickupArea> pickupAreas = pickupAreaUsecase.getAllPickupArea();

        //chuyển từ List<PickupArea> sang List<PickupAreaDTO>
        //dùng mapper
        return pickupAreaDTOMapper.toPickupAreaDTOs(pickupAreas);

    }

    //thêm
    @PostMapping("/add")
    public PickupAreaDTO add(@RequestBody SavePickupAreaDTO pickupAreaDTO) {

        PickupArea pickupArea = pickupAreaDTOMapper.toPickupArea(pickupAreaDTO);

        //lấy id từ dto rồi set vào entity
        City city = new City();
        city.setId(pickupAreaDTO.getCityId());

        pickupArea.setCity(city);

        pickupArea = pickupAreaUsecase.addPickupArea(pickupArea);

        return pickupAreaDTOMapper.toPickupAreaDTO(pickupArea);

    }

    //xóa
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable String id) {
        pickupAreaUsecase.deletePickupArea(id);
    }

    //sửa
    @PutMapping("/update/{id}")
    public PickupAreaDTO update(@RequestBody SavePickupAreaDTO pickupAreaDTO, @PathVariable String id) {

        PickupArea pickupArea = pickupAreaDTOMapper.toPickupArea(pickupAreaDTO);

        //lấy id từ dto rồi set vào entity
        City city = new City();
        city.setId(pickupAreaDTO.getCityId());

        pickupArea.setCity(city);

        pickupArea = pickupAreaUsecase.updatePickupArea(pickupArea, id);

        return pickupAreaDTOMapper.toPickupAreaDTO(pickupArea);

    }

    //tìm theo id
    @GetMapping("/get/{id}")
    public PickupAreaDTO get(@PathVariable String id) {

        PickupArea pickupArea = pickupAreaUsecase.findPickupAreaById(id);

        return pickupAreaDTOMapper.toPickupAreaDTO(pickupArea);

    }
}

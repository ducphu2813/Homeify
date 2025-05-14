package com.homeify.serviceinfo.serviceinfoapi.Mapper;


import com.homeify.serviceinfo.Entities.PickupArea;
import com.homeify.serviceinfo.serviceinfoapi.DTO.PickupArea.PickupAreaDTO;
import com.homeify.serviceinfo.serviceinfoapi.DTO.PickupArea.SavePickupAreaDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {CityDTOMapper.class})
public interface PickupAreaDTOMapper {

    //chuyển từ PickupArea sang PickupAreaDTO
    PickupAreaDTO toPickupAreaDTO(PickupArea pickupArea);

    //chuyển từ PickupAreaDTO sang PickupArea
    PickupArea toPickupArea(PickupAreaDTO pickupAreaDTO);

    //chuyển từ List<PickupArea> sang List<PickupAreaDTO>
    List<PickupAreaDTO> toPickupAreaDTOs(List<PickupArea> pickupAreas);

    //chuyển từ List<PickupAreaDTO> sang List<PickupArea>
    List<PickupArea> toPickupAreas(List<PickupAreaDTO> pickupAreaDTOs);

    //chuyển từ SavePickupAreaDTO sang PickupArea
    PickupArea toPickupArea(SavePickupAreaDTO savePickupAreaDTO);
}

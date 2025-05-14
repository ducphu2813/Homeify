package com.homeify.serviceinfo.serviceinfoapi.Mapper;

import com.homeify.serviceinfo.Entities.TripInfo;
import com.homeify.serviceinfo.serviceinfoapi.DTO.TripInfo.SaveTripInfoDTO;
import com.homeify.serviceinfo.serviceinfoapi.DTO.TripInfo.TripInfoDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {CityDTOMapper.class})
public interface TripInfoDTOMapper {

    //chuyển từ TripInfo sang TripInfoDTO
    TripInfoDTO toTripInfoDTO(TripInfo tripInfo);

    //chuyển từ TripInfoDTO sang TripInfo
    TripInfo toTripInfo(TripInfoDTO tripInfoDTO);

    //chuyển từ List<TripInfo> sang List<TripInfoDTO>
    List<TripInfoDTO> toTripInfoDTOs(List<TripInfo> tripInfos);

    //chuyển từ List<TripInfoDTO> sang List<TripInfo>
    List<TripInfo> toTripInfos(List<TripInfoDTO> tripInfoDTOs);

    //chuyển từ SaveTripInfoDTO sang TripInfo
    TripInfo toTripInfo(SaveTripInfoDTO saveTripInfoDTO);
}

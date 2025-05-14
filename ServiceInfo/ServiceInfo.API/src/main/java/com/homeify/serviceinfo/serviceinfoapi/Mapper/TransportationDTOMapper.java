package com.homeify.serviceinfo.serviceinfoapi.Mapper;

import com.homeify.serviceinfo.Entities.Transportation;
import com.homeify.serviceinfo.serviceinfoapi.DTO.Transportation.SaveTransportDTO;
import com.homeify.serviceinfo.serviceinfoapi.DTO.Transportation.TransportationDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {SeatDTOMapper.class})
public interface TransportationDTOMapper {

    //chuyển từ Transportation sang TransportationDTO
    TransportationDTO toTransportationDTO(Transportation transportation);

    //chuyển từ TransportationDTO sang Transportation
    Transportation toTransportation(TransportationDTO transportationDTO);

    //chuyển từ List<Transportation> sang List<TransportationDTO>
    List<TransportationDTO> toTransportationDTOs(List<Transportation> transportations);

    //chuyển từ List<TransportationDTO> sang List<Transportation>
    List<Transportation> toTransportations(List<TransportationDTO> transportationDTOs);

    //chuyển từ Transportation sang SaveTransportationDTO
    SaveTransportDTO toSaveTransportationDTO(Transportation transportation);

    //chuyển từ SaveTransportationDTO sang Transportation
    Transportation toTransportation(SaveTransportDTO saveTransportDTO);
}

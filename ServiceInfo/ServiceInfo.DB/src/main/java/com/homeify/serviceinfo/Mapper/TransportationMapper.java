package com.homeify.serviceinfo.Mapper;

import com.homeify.serviceinfo.Entities.Transportation;
import com.homeify.serviceinfo.Model.TransportationModel;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface TransportationMapper {

    //chuyển từ TransportationModel sang Transportation
    Transportation toTransportation(TransportationModel transportationModel);

    //chuyển từ Transportation sang TransportationModel
    TransportationModel toTransportationModel(Transportation transportation);

    //chuyển từ List<TransportationModel> sang List<Transportation>
    List<Transportation> toTransportation(List<TransportationModel> transportationModel);

    //chuyển từ List<Transportation> sang List<TransportationModel>
    List<TransportationModel> toTransportationModel(List<Transportation> transportation);
}

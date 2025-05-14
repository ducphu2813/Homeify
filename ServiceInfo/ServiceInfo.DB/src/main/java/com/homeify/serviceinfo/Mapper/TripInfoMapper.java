package com.homeify.serviceinfo.Mapper;

import com.homeify.serviceinfo.Entities.TripInfo;
import org.mapstruct.Mapper;
import com.homeify.serviceinfo.Model.TripInfoModel;

import java.util.List;

@Mapper(uses = {CityMapper.class})
public interface TripInfoMapper {

    //chuyển từ TripInfoModel sang TripInfo
    TripInfo toTripInfo(TripInfoModel tripInfoModel);

    //chuyển từ TripInfo sang TripInfoModel
    TripInfoModel toTripInfoModel(TripInfo tripInfo);

    //chuyển từ List<TripInfoModel> sang List<TripInfo>
    List<TripInfo> toTripInfo(List<TripInfoModel> tripInfoModel);

    //chuyển từ List<TripInfo> sang List<TripInfoModel>
    List<TripInfoModel> toTripInfoModel(List<TripInfo> tripInfo);
}

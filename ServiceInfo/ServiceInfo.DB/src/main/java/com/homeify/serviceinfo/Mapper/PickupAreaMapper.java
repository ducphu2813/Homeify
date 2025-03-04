package com.homeify.serviceinfo.Mapper;

import com.homeify.serviceinfo.Entities.PickupArea;
import com.homeify.serviceinfo.Model.PickupAreaModel;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface PickupAreaMapper {

    //chuyển từ PickupAreaModel sang PickupArea
    PickupArea toPickupArea(PickupAreaModel pickupAreaModel);

    //chuyển từ PickupArea sang PickupAreaModel
    PickupAreaModel toPickupAreaModel(PickupArea pickupArea);

    //chuyển từ List<PickupAreaModel> sang List<PickupArea>
    List<PickupArea> toPickupArea(List<PickupAreaModel> pickupAreaModel);

    //chuyển từ List<PickupArea> sang List<PickupAreaModel>
    List<PickupAreaModel> toPickupAreaModel(List<PickupArea> pickupArea);
}

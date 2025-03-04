package com.homeify.serviceinfo.Adapter;

import com.homeify.serviceinfo.Entities.PickupArea;

import java.util.List;

public interface PickupAreaAdapter {

    //thêm pickup area
    PickupArea addPickupArea(PickupArea pickupArea);

    //sửa pickup area
    PickupArea updatePickupArea(PickupArea pickupArea, String pickupAreaId);

    //xóa pickup area
    void deletePickupArea(String pickupAreaId);

    //lấy tat ca pickup area
    List<PickupArea> getAllPickupArea();

    //tìm theo id
    PickupArea findPickupAreaById(String pickupAreaId);
}

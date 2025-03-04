package com.homeify.serviceinfo.UseCases;

import com.homeify.serviceinfo.Adapter.PickupAreaAdapter;
import com.homeify.serviceinfo.Entities.PickupArea;

import java.util.List;

public class PickupAreaUsecase {

    private final PickupAreaAdapter pickupAreaAdapter;

    public PickupAreaUsecase(PickupAreaAdapter pickupAreaAdapter) {
        this.pickupAreaAdapter = pickupAreaAdapter;
    }

    //thêm pickup area
    public PickupArea addPickupArea(PickupArea pickupArea) {
        return pickupAreaAdapter.addPickupArea(pickupArea);
    }

    //sửa pickup area
    public PickupArea updatePickupArea(PickupArea pickupArea, String pickupAreaId) {
        return pickupAreaAdapter.updatePickupArea(pickupArea, pickupAreaId);
    }

    //xóa pickup area
    public void deletePickupArea(String pickupAreaId) {
        pickupAreaAdapter.deletePickupArea(pickupAreaId);
    }

    //lấy tất cả pickup area
    public List<PickupArea> getAllPickupArea() {
        return pickupAreaAdapter.getAllPickupArea();
    }

    //tìm theo id
    public PickupArea findPickupAreaById(String pickupAreaId) {
        return pickupAreaAdapter.findPickupAreaById(pickupAreaId);
    }
}

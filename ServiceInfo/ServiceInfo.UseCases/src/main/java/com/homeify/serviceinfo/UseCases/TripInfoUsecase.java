package com.homeify.serviceinfo.UseCases;

import com.homeify.serviceinfo.Adapter.TripInfoAdapter;
import com.homeify.serviceinfo.Entities.TripInfo;

import java.util.List;

public class TripInfoUsecase {

    private final TripInfoAdapter tripInfoAdapter;

    public TripInfoUsecase(TripInfoAdapter tripInfoAdapter) {
        this.tripInfoAdapter = tripInfoAdapter;
    }

    //thêm trip info
    public TripInfo addTripInfo(TripInfo tripInfo) {
        return tripInfoAdapter.addTripInfo(tripInfo);
    }

    //sửa trip info
    public TripInfo updateTripInfo(TripInfo tripInfo, String tripInfoId) {
        return tripInfoAdapter.updateTripInfo(tripInfo, tripInfoId);
    }

    //xóa trip info
    public void deleteTripInfo(String tripInfoId) {
        tripInfoAdapter.deleteTripInfo(tripInfoId);
    }

    //lấy tất cả trip info
    public List<TripInfo> getAllTripInfo() {
        return tripInfoAdapter.getAllTripInfo();
    }

    //tìm theo id
    public TripInfo findTripInfoById(String tripInfoId) {
        return tripInfoAdapter.findTripInfoById(tripInfoId);
    }
}

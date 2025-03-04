package com.homeify.serviceinfo.Adapter;

import com.homeify.serviceinfo.Entities.TripInfo;

import java.util.List;

public interface TripInfoAdapter {

    //thêm trip info
    TripInfo addTripInfo(TripInfo tripInfo);

    //sửa trip info
    TripInfo updateTripInfo(TripInfo tripInfo, String tripInfoId);

    //xóa trip info
    void deleteTripInfo(String tripInfoId);

    //lấy tat ca trip info
    List<TripInfo> getAllTripInfo();

    //tìm theo id
    TripInfo findTripInfoById(String tripInfoId);
}

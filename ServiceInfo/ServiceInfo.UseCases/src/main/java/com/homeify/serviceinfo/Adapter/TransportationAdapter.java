package com.homeify.serviceinfo.Adapter;

import com.homeify.serviceinfo.Entities.Transportation;

import java.util.List;

public interface TransportationAdapter {

    //thêm phương tiện
    Transportation addTransportation(Transportation transportation);

    //sửa phương tiện
    Transportation updateTransportation(Transportation transportation, String transportationId);

    //xóa phương tiện
    void deleteTransportation(String transportationId);

    //lấy tất cả phương tiện
    List<Transportation> getAllTransportation();

    //tìm theo id
    Transportation findTransportationById(String transportationId);
}

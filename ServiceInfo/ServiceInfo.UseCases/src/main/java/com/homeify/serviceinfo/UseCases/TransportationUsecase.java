package com.homeify.serviceinfo.UseCases;

import com.homeify.serviceinfo.Adapter.KafkaEventConsumerAdapter;
import com.homeify.serviceinfo.Adapter.TransportationAdapter;
import com.homeify.serviceinfo.Entities.Transportation;

import java.util.List;

public class TransportationUsecase {

    private final TransportationAdapter transportationAdapter;

    public TransportationUsecase(TransportationAdapter transportationAdapter) {
        this.transportationAdapter = transportationAdapter;
    }

    //thêm phương tiện
    public Transportation addTransportation(Transportation transportation) {
        return transportationAdapter.addTransportation(transportation);
    }

    //sửa phương tiện
    public Transportation updateTransportation(Transportation transportation, String transportationId) {
        return transportationAdapter.updateTransportation(transportation, transportationId);
    }

    //xóa phương tiện
    public void deleteTransportation(String transportationId) {
        transportationAdapter.deleteTransportation(transportationId);
    }

    //lấy tất cả phương tiện
    public List<Transportation> getAllTransportation() {
        return transportationAdapter.getAllTransportation();
    }

    //tìm theo id
    public Transportation findTransportationById(String transportationId) {
        return transportationAdapter.findTransportationById(transportationId);
    }
}

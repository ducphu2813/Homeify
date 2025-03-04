package com.homeify.serviceinfo.AdapterImpl;

import com.homeify.serviceinfo.Adapter.TransportationAdapter;
import com.homeify.serviceinfo.Entities.Transportation;
import com.homeify.serviceinfo.Mapper.TransportationMapper;
import com.homeify.serviceinfo.Model.TransportationModel;
import com.homeify.serviceinfo.Repository.TransportationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TransportationAdapterImpl implements TransportationAdapter {

    private final TransportationRepository transportationRepository;
    private final TransportationMapper transportationMapper;

    public TransportationAdapterImpl(TransportationRepository transportationRepository
                                        , TransportationMapper transportationMapper) {
        this.transportationRepository = transportationRepository;
        this.transportationMapper = transportationMapper;
    }


    @Override
    @Transactional
    public Transportation addTransportation(Transportation transportation) {

        //chjuyen doi tu entity sang model
        TransportationModel transportationModel = transportationMapper.toTransportationModel(transportation);

        //tự động tạo id
        transportationModel.setId(generateId());

        //luu vao db
        transportationModel = transportationRepository.save(transportationModel);

        //chuyen doi tu model sang entity
        return transportationMapper.toTransportation(transportationModel);

    }

    @Override
    @Transactional
    public Transportation updateTransportation(Transportation transportation, String transportationId) {

        //tim theo id
        TransportationModel transportationModel = transportationRepository.findById(transportationId).orElse(null);

        if (transportationModel == null) {
            return null;
        }

        //dùng mapper
        transportationModel = transportationMapper.toTransportationModel(transportation);

        //set id cũ
        transportationModel.setId(transportationId);

        transportationModel = transportationRepository.save(transportationModel);

        return transportationMapper.toTransportation(transportationModel);
    }

    @Override
    @Transactional
    public void deleteTransportation(String transportationId) {
        transportationRepository.deleteById(transportationId);
    }

    @Override
    public List<Transportation> getAllTransportation() {
        List<TransportationModel> transportationModels = transportationRepository.findAll();

        return transportationMapper.toTransportation(transportationModels);
    }

    @Override
    public Transportation findTransportationById(String transportationId) {
        TransportationModel transportationModel = transportationRepository.findById(transportationId).orElse(null);

        return transportationMapper.toTransportation(transportationModel);
    }

    //tự động tạo id(XE001, XE002, ...)
    private String generateId() {
        TransportationModel lastTransportation = transportationRepository.findTopByOrderByIdDesc();
        if (lastTransportation == null) {
            return "XE001";
        }
        String lastId = lastTransportation.getId();
        int number = Integer.parseInt(lastId.substring(2));
        return "XE" + String.format("%03d", number + 1);
    }
}

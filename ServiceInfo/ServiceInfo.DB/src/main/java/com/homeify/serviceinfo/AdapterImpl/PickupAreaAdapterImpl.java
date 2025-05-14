package com.homeify.serviceinfo.AdapterImpl;

import com.homeify.serviceinfo.Adapter.PickupAreaAdapter;
import com.homeify.serviceinfo.Entities.City;
import com.homeify.serviceinfo.Entities.PickupArea;
import com.homeify.serviceinfo.Mapper.CityMapper;
import com.homeify.serviceinfo.Mapper.PickupAreaMapper;
import com.homeify.serviceinfo.Model.CityModel;
import com.homeify.serviceinfo.Model.PickupAreaModel;
import com.homeify.serviceinfo.Repository.CityRepository;
import com.homeify.serviceinfo.Repository.PickupAreaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
public class PickupAreaAdapterImpl implements PickupAreaAdapter {

    private final PickupAreaRepository pickupAreaRepository;
    private final PickupAreaMapper pickupAreaMapper;

    public PickupAreaAdapterImpl(PickupAreaRepository pickupAreaRepository
                                , PickupAreaMapper pickupAreaMapper
    ) {
        this.pickupAreaRepository = pickupAreaRepository;
        this.pickupAreaMapper = pickupAreaMapper;
    }


    @Override
    @Transactional
    public PickupArea addPickupArea(PickupArea pickupArea) {
        //chuyển từ PickupArea sang PickupAreaModel
        PickupAreaModel pickupAreaModel = pickupAreaMapper.toPickupAreaModel(pickupArea);

        //set id
        pickupAreaModel.setId(generateId());

        //lưu vào db
        pickupAreaRepository.save(pickupAreaModel);

        //chuyển từ PickupAreaModel sang PickupArea
        return pickupAreaMapper.toPickupArea(pickupAreaModel);
    }

    @Override
    @Transactional
    public PickupArea updatePickupArea(PickupArea pickupArea, String pickupAreaId) {
        //chuyển từ PickupArea sang PickupAreaModel
        PickupAreaModel pickupAreaModel = pickupAreaMapper.toPickupAreaModel(pickupArea);

        //set id
        pickupAreaModel.setId(pickupAreaId);

        //lưu vào db
        pickupAreaRepository.save(pickupAreaModel);

        //chuyển từ PickupAreaModel sang PickupArea
        return pickupAreaMapper.toPickupArea(pickupAreaModel);
    }

    @Override
    @Transactional
    public void deletePickupArea(String pickupAreaId) {
        pickupAreaRepository.deleteById(pickupAreaId);
    }


    @Override
    public List<PickupArea> getAllPickupArea() {
        //tìm theo id
        List<PickupAreaModel> pickupAreaModels = pickupAreaRepository.findAll();

        //dùng mapper
        return pickupAreaMapper.toPickupArea(pickupAreaModels);
    }


    @Override
    public PickupArea findPickupAreaById(String pickupAreaId) {
        //tìm theo id
        PickupAreaModel pickupAreaModel = pickupAreaRepository.findById(pickupAreaId).orElse(null);

        if(pickupAreaModel == null) {
            return null;
        }

        //dùng mapper
        return pickupAreaMapper.toPickupArea(pickupAreaModel);
    }

    //tự động tạo id(PA001, PA002, ...)
    private String generateId() {

        PickupAreaModel pickupAreaModel = pickupAreaRepository.findTopByOrderByIdDesc();
        if(pickupAreaModel == null) {
            return "PA001";
        }
        String id = pickupAreaModel.getId();
        String prefix = id.substring(0, 2);
        String suffix = id.substring(2);
        int number = Integer.parseInt(suffix);
        number++;
        return prefix + String.format("%03d", number);
    }
}

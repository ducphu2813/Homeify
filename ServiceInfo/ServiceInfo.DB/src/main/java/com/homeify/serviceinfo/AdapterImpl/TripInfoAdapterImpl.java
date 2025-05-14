package com.homeify.serviceinfo.AdapterImpl;

import com.homeify.serviceinfo.Adapter.TripInfoAdapter;
import com.homeify.serviceinfo.Entities.TripInfo;
import com.homeify.serviceinfo.Mapper.CityMapper;
import com.homeify.serviceinfo.Mapper.TripInfoMapper;
import com.homeify.serviceinfo.Model.CityModel;
import com.homeify.serviceinfo.Model.TripInfoModel;
import com.homeify.serviceinfo.Repository.CityRepository;
import com.homeify.serviceinfo.Repository.TripInfoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class TripInfoAdapterImpl implements TripInfoAdapter {

    private final TripInfoRepository tripInfoRepository;
    private final TripInfoMapper tripInfoMapper;

    public TripInfoAdapterImpl(TripInfoRepository tripInfoRepository
                                , TripInfoMapper tripInfoMapper
    ) {
        this.tripInfoRepository = tripInfoRepository;
        this.tripInfoMapper = tripInfoMapper;
    }

    @Override
    @Transactional
    public TripInfo addTripInfo(TripInfo tripInfo) {
        //chuyyển từ TripInfo sang TripInfoModel
        TripInfoModel tripInfoModel = tripInfoMapper.toTripInfoModel(tripInfo);

        //set id
        tripInfoModel.setId(generateId());

        //lưu vào db
        tripInfoRepository.save(tripInfoModel);

        //chuyển từ TripInfoModel sang TripInfo
        return tripInfoMapper.toTripInfo(tripInfoModel);
    }

    @Override
    @Transactional
    public TripInfo updateTripInfo(TripInfo tripInfo, String tripInfoId) {
        //tìm theo id
        TripInfoModel tripInfoModel = tripInfoRepository.findById(tripInfoId).orElse(null);

        if(tripInfoModel == null) {
            return null;
        }

        //chuyển từ TripInfo sang TripInfoModel
        tripInfoModel = tripInfoMapper.toTripInfoModel(tripInfo);

        //set id cũ của tripInfo
        tripInfoModel.setId(tripInfoId);

        //lưu vào db
        tripInfoRepository.save(tripInfoModel);

        //chuyển từ TripInfoModel sang TripInfo
        return tripInfoMapper.toTripInfo(tripInfoModel);
    }

    @Override
    @Transactional
    public void deleteTripInfo(String tripInfoId) {
        //xóa theo id
        tripInfoRepository.deleteById(tripInfoId);
    }


    @Override
    public List<TripInfo> getAllTripInfo() {
        List<TripInfoModel> tripInfoModelList = tripInfoRepository.findAll();

        //dùng mapper
        return tripInfoMapper.toTripInfo(tripInfoModelList);
    }

    @Override
    public TripInfo findTripInfoById(String tripInfoId) {

        //tìm theo id
        TripInfoModel tripInfoModel = tripInfoRepository.findById(tripInfoId).orElse(null);

        if(tripInfoModel == null) {
            return null;
        }

        //dùng mapper
        return tripInfoMapper.toTripInfo(tripInfoModel);
    }

    //tự động tạo id(CH0001, CH0002, ...)
    private String generateId() {
        TripInfoModel tripInfoModel = tripInfoRepository.findTopByOrderByIdDesc();
        if (tripInfoModel == null) {
            return "CH0001";
        }
        String id = tripInfoModel.getId();
        String prefix = id.substring(0, 2);
        String suffix = id.substring(2);
        int number = Integer.parseInt(suffix);
        number++;
        return prefix + String.format("%04d", number);
    }
}

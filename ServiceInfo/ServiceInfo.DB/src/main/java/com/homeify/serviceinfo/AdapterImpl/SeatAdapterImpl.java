package com.homeify.serviceinfo.AdapterImpl;

import com.homeify.serviceinfo.Adapter.SeatAdapter;
import com.homeify.serviceinfo.Entities.Seat;
import com.homeify.serviceinfo.Entities.Transportation;
import com.homeify.serviceinfo.Mapper.SeatMapper;
import com.homeify.serviceinfo.Mapper.TransportationMapper;
import com.homeify.serviceinfo.Model.SeatModel;
import com.homeify.serviceinfo.Model.TransportationModel;
import com.homeify.serviceinfo.Repository.SeatRepository;
import com.homeify.serviceinfo.Repository.TransportationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class SeatAdapterImpl implements SeatAdapter {

    private final SeatRepository seatRepository;
    private final TransportationRepository transportationRepository;

    private final TransportationMapper transportationMapper;
    private final SeatMapper seatMapper;

    public SeatAdapterImpl(SeatRepository seatRepository
                            , SeatMapper seatMapper
                            , TransportationRepository transportationRepository
                            , TransportationMapper transportationMapper) {
        this.seatRepository = seatRepository;
        this.seatMapper = seatMapper;
        this.transportationRepository = transportationRepository;
        this.transportationMapper = transportationMapper;
    }


    @Override
    @Transactional
    public Seat addSeat(Seat seat) {
        //dùng mapper chuyển từ entity sang model
        SeatModel seatModel = seatMapper.toSeatModel(seat);

        //tự động tạo id
        seatModel.setId(generateId());

        TransportationModel transportationModel = new TransportationModel();
        transportationModel.setId(seat.getTransportation().getId());

        seatModel.setTransportation(transportationModel);

        seatModel = seatRepository.save(seatModel);

        //dùng mapper chuyển từ model sang entity
        return seatMapper.toSeat(seatModel);
    }

    @Override
    @Transactional
    public Seat updateSeat(Seat seat, String seatId) {
        //tìm theo id
        SeatModel seatModel = seatRepository.findById(seatId).orElse(null);

        if (seatModel == null) {
            return null;
        }

        //dùng mapper
        seatModel = seatMapper.toSeatModel(seat);

        //set id cũ cho seatModel
        seatModel.setId(seatId);

        //set transportation
        TransportationModel transportationModel = new TransportationModel();
        transportationModel.setId(seat.getTransportation().getId());

        seatModel.setTransportation(transportationModel);

        seatModel = seatRepository.save(seatModel);

        return seatMapper.toSeat(seatModel);
    }

    @Override
    @Transactional
    public void deleteSeat(String seatId) {
        seatRepository.deleteById(seatId);
    }

    @Override
    public List<Seat> getAllSeat() {

        List<SeatModel> seatModels = seatRepository.findAll();

        //dùng mapper
        return seatMapper.toSeat(seatModels);
    }

    @Override
    public Seat findSeatById(String seatId) {
        SeatModel seatModel = seatRepository.findById(seatId).orElse(null);

        if (seatModel == null) {
            return null;
        }

        return seatMapper.toSeat(seatModel);
    }

    //tự động tạo id(S001, S002, S003,...)
    private String generateId() {
        SeatModel seat = seatRepository.findTopByOrderByIdDesc();
        if (seat == null) {
            return "S001";
        }
        String id = seat.getId();
        String prefix = id.substring(0, 1);
        int number = Integer.parseInt(id.substring(1));
        number++;
        return prefix + String.format("%03d", number);
    }

}

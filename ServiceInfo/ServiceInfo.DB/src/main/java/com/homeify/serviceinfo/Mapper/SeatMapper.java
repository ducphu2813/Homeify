package com.homeify.serviceinfo.Mapper;

import com.homeify.serviceinfo.Entities.Seat;

import com.homeify.serviceinfo.Model.SeatModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface SeatMapper {

    //chuyển từ SeatModel sang Seat
    @Mapping(target = "transportation", ignore = true)
    Seat toSeat(SeatModel seatModel);

    //chuyển từ Seat sang SeatModel
    @Mapping(target = "transportation", ignore = true)
    SeatModel toSeatModel(Seat seat);

    //chuyển từ List<SeatModel> sang List<Seat>
    @Mapping(target = "transportation", ignore = true)
    List<Seat> toSeat(List<SeatModel> seatModel);

    //chuyển từ List<Seat> sang List<SeatModel>
    @Mapping(target = "transportation", ignore = true)
    List<SeatModel> toSeatModel(List<Seat> seat);

}

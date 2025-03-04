package com.homeify.serviceinfo.Mapper;

import com.homeify.serviceinfo.Entities.Seat;

import com.homeify.serviceinfo.Model.SeatModel;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface SeatMapper {

    //chuyển từ SeatModel sang Seat
    Seat toSeat(SeatModel seatModel);

    //chuyển từ Seat sang SeatModel
    SeatModel toSeatModel(Seat seat);

    //chuyển từ List<SeatModel> sang List<Seat>
    List<Seat> toSeat(List<SeatModel> seatModel);

    //chuyển từ List<Seat> sang List<SeatModel>
    List<SeatModel> toSeatModel(List<Seat> seat);

}

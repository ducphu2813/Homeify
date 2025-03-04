package com.homeify.serviceinfo.serviceinfoapi.Mapper;

import com.homeify.serviceinfo.Entities.Seat;
import com.homeify.serviceinfo.serviceinfoapi.DTO.Seat.SaveSeatDTO;
import com.homeify.serviceinfo.serviceinfoapi.DTO.Seat.SeatDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SeatDTOMapper {

    //chuyển từ Seat sang SeatDTO
    SeatDTO toSeatDTO(Seat seat);

    //chuyển từ SeatDTO sang Seat
    Seat toSeat(SeatDTO seatDTO);

    //chuyển từ List<Seat> sang List<SeatDTO>
    List<SeatDTO> toSeatDTOs(List<Seat> seats);

    //chuyển từ List<SeatDTO> sang List<Seat>
    List<Seat> toSeats(List<SeatDTO> seatDTOs);

    //chuyển từ Seat sang SaveSeatDTO
    SaveSeatDTO toSaveSeatDTO(Seat seat);

    //chuyển từ SaveSeatDTO sang Seat
    Seat toSeat(SaveSeatDTO saveSeatDTO);
}

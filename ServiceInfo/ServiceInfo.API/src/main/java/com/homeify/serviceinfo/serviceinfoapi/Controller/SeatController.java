package com.homeify.serviceinfo.serviceinfoapi.Controller;

import com.homeify.serviceinfo.Entities.Seat;
import com.homeify.serviceinfo.Entities.Transportation;
import com.homeify.serviceinfo.UseCases.SeatUsecase;
import com.homeify.serviceinfo.serviceinfoapi.DTO.Seat.SaveSeatDTO;
import com.homeify.serviceinfo.serviceinfoapi.DTO.Seat.SeatDTO;
import com.homeify.serviceinfo.serviceinfoapi.Mapper.SeatDTOMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/seat")
public class SeatController {

    private final SeatUsecase seatUsecase;
    private final SeatDTOMapper seatDTOMapper;

    public SeatController(SeatUsecase seatUsecase, SeatDTOMapper seatDTOMapper) {
        this.seatUsecase = seatUsecase;
        this.seatDTOMapper = seatDTOMapper;
    }

    //get all
    @GetMapping("/getAll")
    public List<SeatDTO> getAll() {

        List<Seat> seats = seatUsecase.getAllSeat();

        //chuyển từ List<Seat> sang List<SeatDTO>
        //dùng mapper
        return seatDTOMapper.toSeatDTOs(seats);

    }

    //thêm
    @PostMapping("/add")
    public SeatDTO add(@RequestBody SaveSeatDTO seatDTO) {

        Seat seat = seatDTOMapper.toSeat(seatDTO);

        Transportation transportation = new Transportation();
        transportation.setId(seatDTO.getTransportationId());

        seat.setTransportation(transportation);

        seat = seatUsecase.addSeat(seat);

        return seatDTOMapper.toSeatDTO(seat);

    }

    //sửa
    @PutMapping("/update/{id}")
    public SeatDTO update(@RequestBody SaveSeatDTO seatDTO, @PathVariable String id) {

        Seat seat = seatDTOMapper.toSeat(seatDTO);

        seat = seatUsecase.updateSeat(seat, id);

        return seatDTOMapper.toSeatDTO(seat);

    }

    //xóa
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable String id) {
        seatUsecase.deleteSeat(id);
    }

    //tìm theo id
    @GetMapping("/getById/{id}")
    public SeatDTO getById(@PathVariable String id) {
        Seat seat = seatUsecase.findSeatById(id);
        if (seat == null) {
            return null;
        }
        return seatDTOMapper.toSeatDTO(seat);
    }
}

package com.homeify.serviceinfo.UseCases;

import com.homeify.serviceinfo.Adapter.SeatAdapter;
import com.homeify.serviceinfo.Entities.Seat;

import java.util.List;

public class SeatUsecase {

    private final SeatAdapter seatAdapter;

    public SeatUsecase(SeatAdapter seatAdapter) {
        this.seatAdapter = seatAdapter;
    }

    //thêm ghế
    public Seat addSeat(Seat seat) {
        return seatAdapter.addSeat(seat);
    }

    //sửa ghế
    public Seat updateSeat(Seat seat, String seatId) {
        return seatAdapter.updateSeat(seat, seatId);
    }

    //xóa ghế
    public void deleteSeat(String seatId) {
        seatAdapter.deleteSeat(seatId);
    }

    //lấy tat ca ghế
    public List<Seat> getAllSeat() {
        return seatAdapter.getAllSeat();
    }

    //tìm theo id
    public Seat findSeatById(String seatId) {
        return seatAdapter.findSeatById(seatId);
    }
}

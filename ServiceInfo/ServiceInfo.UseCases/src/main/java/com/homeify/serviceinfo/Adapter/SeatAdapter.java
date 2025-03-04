package com.homeify.serviceinfo.Adapter;

import com.homeify.serviceinfo.Entities.Seat;

import java.util.List;

public interface SeatAdapter {

    //thêm ghế
    Seat addSeat(Seat seat);

    //sửa ghế
    Seat updateSeat(Seat seat, String seatId);

    //xóa ghế
    void deleteSeat(String seatId);

    //lấy tất cả ghế
    List<Seat> getAllSeat();

    //tìm theo id
    Seat findSeatById(String seatId);
}

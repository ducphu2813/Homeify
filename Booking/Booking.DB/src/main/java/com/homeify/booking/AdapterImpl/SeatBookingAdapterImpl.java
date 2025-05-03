package com.homeify.booking.AdapterImpl;


import com.homeify.booking.Adapter.SeatBookingAdapter;
import com.homeify.booking.Entities.SeatBooking;
import com.homeify.booking.Mapper.SeatBookingMapper;
import com.homeify.booking.Model.SeatBookingModel;
import com.homeify.booking.Model.TripBookingModel;
import com.homeify.booking.Repository.SeatBookingRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class SeatBookingAdapterImpl implements SeatBookingAdapter {

    private final SeatBookingRepository seatBookingRepository;
    private final SeatBookingMapper seatBookingMapper;

    public SeatBookingAdapterImpl(SeatBookingRepository seatBookingRepository
                                    , SeatBookingMapper seatBookingMapper) {
        this.seatBookingRepository = seatBookingRepository;
        this.seatBookingMapper = seatBookingMapper;
    }

    @Override
    @Transactional
    public SeatBooking addSeatBooking(SeatBooking seatBooking) {
        //dùng mapper
        SeatBookingModel seatBookingModel = seatBookingMapper.toSeatBookingModel(seatBooking);

        //set id mới cho seat booking
        String id = generateSeatBookingId();
        seatBookingModel.setId(id);

        //set id của trip booking
        TripBookingModel tripBookingModel = new TripBookingModel();
        tripBookingModel.setId(seatBooking.getTripBooking().getId());

        seatBookingModel.setTripBooking(tripBookingModel);

        seatBookingModel = seatBookingRepository.save(seatBookingModel);

        return seatBookingMapper.toSeatBooking(seatBookingModel);
    }

    @Override
    @Transactional
    public SeatBooking updateSeatBooking(SeatBooking seatBooking, String id) {
        //tìm theo id
        SeatBookingModel seatBookingModel = seatBookingRepository.findById(id).orElse(null);

        if (seatBookingModel == null) {
            return null;
        }

        //dùng mapper
        seatBookingModel = seatBookingMapper.toSeatBookingModel(seatBooking);

        //set id cũ cho seat booking
        seatBookingModel.setId(id);

        //set id của trip booking
        TripBookingModel tripBookingModel = new TripBookingModel();
        tripBookingModel.setId(seatBooking.getTripBooking().getId());

        seatBookingModel.setTripBooking(tripBookingModel);

        seatBookingModel = seatBookingRepository.save(seatBookingModel);

        return seatBookingMapper.toSeatBooking(seatBookingModel);
    }

    @Override
    @Transactional
    public void deleteSeatBooking(String seatBookingId) {
        //xóa theo id
        seatBookingRepository.deleteById(seatBookingId);
    }

    @Override
    public List<SeatBooking> getAllSeatBookings() {
        //lấy tất cả seat booking
        List<SeatBookingModel> seatBookingModels = seatBookingRepository.findAll();

        //dùng mapper
        return seatBookingMapper.toSeatBookings(seatBookingModels);
    }

    @Override
    public SeatBooking findSeatBookingById(String seatBookingId) {
        //tìm theo id
        SeatBookingModel seatBookingModel = seatBookingRepository.findById(seatBookingId).orElse(null);

        if (seatBookingModel == null) {
            return null;
        }

        //dùng mapper
        return seatBookingMapper.toSeatBooking(seatBookingModel);
    }

    @Override
    public List<SeatBooking> findConflictingSeats(List<String> seatIds, List<String> tripBookingIds) {
        //tìm tất cả seat booking theo danh sách seatId và danh sách tripBookingId

        List<SeatBookingModel> bookedSeat = seatBookingRepository.findConflictingSeats(seatIds, tripBookingIds);

        //dùng mapper
        return seatBookingMapper.toSeatBookings(bookedSeat);
    }

    // Kiểm tra những seat_id đã tồn tại trong các TripBooking cùng tripId
    @Override
    public List<SeatBooking> findExistingBookedSeats(List<String> tripBookingIds, List<String> seatIds) {
        //tìm tất cả seat booking theo danh sách tripBookingId và danh sách seatId
        List<SeatBookingModel> bookedSeat = seatBookingRepository.findExistingBookedSeats(tripBookingIds, seatIds);

        //dùng mapper
        return seatBookingMapper.toSeatBookings(bookedSeat);
    }

    //tìm các SeatBooking theo tripBookingId và List seatId
    @Override
    public List<SeatBooking> findByTripBookingIdAndSeatId(String tripBookingId, List<String> seatIds) {
        //tìm tất cả seat booking theo tripBookingId và seatId
        List<SeatBookingModel> bookedSeat = seatBookingRepository.findByTripBookingIdAndSeatId(tripBookingId, seatIds);

        //dùng mapper
        return seatBookingMapper.toSeatBookings(bookedSeat);
    }

    //lấy danh sách id seats đã đặt theo tripId
    @Override
    public List<String> findBookedSeatIdsByTripId(String tripId) {
        //lấy danh sách id seats đã đặt theo tripId
        return seatBookingRepository.findBookedSeatIdsByTripId(tripId);
    }


    //tự sinh id cho seat booking
    private String generateSeatBookingId() {
        //tạo id ngẫu nhiên
        return String.valueOf(System.currentTimeMillis());
    }
}

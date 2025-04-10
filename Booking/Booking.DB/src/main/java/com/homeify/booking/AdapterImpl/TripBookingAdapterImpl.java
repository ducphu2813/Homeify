package com.homeify.booking.AdapterImpl;

import com.homeify.booking.Adapter.TripBookingAdapter;
import com.homeify.booking.Entities.TripBooking;
import com.homeify.booking.Mapper.TripBookingMapper;
import com.homeify.booking.Model.BookingModel;
import com.homeify.booking.Model.TripBookingModel;
import com.homeify.booking.Repository.TripBookingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TripBookingAdapterImpl implements TripBookingAdapter {

    private final TripBookingRepository tripBookingRepository;
    private final TripBookingMapper tripBookingMapper;

    public TripBookingAdapterImpl(TripBookingRepository tripBookingRepository, TripBookingMapper tripBookingMapper) {
        this.tripBookingRepository = tripBookingRepository;
        this.tripBookingMapper = tripBookingMapper;
    }

    @Override
    @Transactional
    public TripBooking addTripBooking(TripBooking tripBooking) {

        //dùng mapper
        TripBookingModel tripBookingModel = tripBookingMapper.toTripBookingModel(tripBooking);

        BookingModel booking = new BookingModel();

        booking.setId(tripBooking.getBooking().getId());

        tripBookingModel.setBooking(booking);

        //set id mới cho trip booking
        String id = generateTripBookingId();
        tripBookingModel.setId(id);

        tripBookingModel = tripBookingRepository.save(tripBookingModel);

        return tripBookingMapper.toTripBooking(tripBookingModel);
    }

    @Override
    @Transactional
    public TripBooking updateTripBooking(TripBooking tripBooking, String id) {

        //tìm theo id
        TripBookingModel tripBookingModel = tripBookingRepository.findById(id).orElse(null);

        if (tripBookingModel == null) {
            return null;
        }

        //dùng mapper
        tripBookingModel = tripBookingMapper.toTripBookingModel(tripBooking);

        //set lại id cũ
        tripBookingModel.setId(id);

        BookingModel booking = new BookingModel();
        booking.setId(tripBooking.getBooking().getId());
        tripBookingModel.setBooking(booking);

        tripBookingModel = tripBookingRepository.save(tripBookingModel);

        return tripBookingMapper.toTripBooking(tripBookingModel);
    }

    @Override
    @Transactional
    public void deleteTripBooking(String tripBookingId) {
        tripBookingRepository.deleteById(tripBookingId);
    }

    @Override
    public List<TripBooking> getAllTripBookings() {
        return List.of();
    }

    @Override
    public TripBooking findTripBookingById(String tripBookingId) {
        return null;
    }


    //tự động tạo id cho trip booking theo năm tháng ngày giờ phút giây
    //VD: TB20231010123456
    //TB + năm + tháng + ngày + giờ + phút + giây
    public String generateTripBookingId() {
        long currentTimeMillis = System.currentTimeMillis();
        String tripBookingId = "TB" + currentTimeMillis;
        return tripBookingId;
    }
}

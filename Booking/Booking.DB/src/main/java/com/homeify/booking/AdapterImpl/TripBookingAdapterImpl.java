package com.homeify.booking.AdapterImpl;

import com.homeify.booking.Adapter.TripBookingAdapter;
import com.homeify.booking.Entities.Booking;
import com.homeify.booking.Entities.TripBooking;
import com.homeify.booking.Mapper.BookingMapper;
import com.homeify.booking.Mapper.TripBookingMapper;
import com.homeify.booking.Model.BookingModel;
import com.homeify.booking.Model.TripBookingModel;
import com.homeify.booking.Repository.BookingRepository;
import com.homeify.booking.Repository.TripBookingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TripBookingAdapterImpl implements TripBookingAdapter {

    private final TripBookingRepository tripBookingRepository;
    private final TripBookingMapper tripBookingMapper;

    private final BookingRepository bookingRepository;
    private final BookingMapper bookingMapper;

    public TripBookingAdapterImpl(TripBookingRepository tripBookingRepository
                                    , TripBookingMapper tripBookingMapper
                                    , BookingRepository bookingRepository
                                    , BookingMapper bookingMapper) {
        this.tripBookingRepository = tripBookingRepository;
        this.tripBookingMapper = tripBookingMapper;
        this.bookingRepository = bookingRepository;
        this.bookingMapper = bookingMapper;
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
        String bookingId = tripBookingModel.getBooking().getId();

        //dùng mapper
        tripBookingModel = tripBookingMapper.toTripBookingModel(tripBooking);
        tripBookingModel.setSeatBookings(null);

        //set lại id cũ
        tripBookingModel.setId(id);

        BookingModel booking = new BookingModel();
        booking.setId(bookingId);
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
        List<TripBookingModel> tripBookingModels = tripBookingRepository.findAll();

        //dùng mapper
        List<TripBooking> tripBookings = tripBookingMapper.toTripBookings(tripBookingModels);

        return tripBookings;
    }

    @Override
    public TripBooking findTripBookingById(String tripBookingId) {
        TripBookingModel tripBookingModel = tripBookingRepository.findById(tripBookingId).orElse(null);

        if (tripBookingModel == null) {
            return null;
        }

        //dùng mapper
        TripBooking tripBooking = tripBookingMapper.toTripBooking(tripBookingModel);

        //lấy booking để set vào trip booking
        BookingModel booking = bookingRepository.findById(tripBooking.getBooking().getId()).orElse(null);
        Booking booking1 = bookingMapper.toBooking(booking);
        tripBooking.setBooking(booking1);

        return tripBooking;
    }

    @Override
    public List<TripBooking> findTripBookingsByTripId(String tripId) {
        List<TripBookingModel> tripBookingModels = tripBookingRepository.findByTripId(tripId);

        //dùng mapper
        List<TripBooking> tripBookings = tripBookingMapper.toTripBookings(tripBookingModels);

        return tripBookings;
    }

    // Tìm TripBooking theo tripId và bookingId
    @Override
    public TripBooking findTripBookingsByTripIdAndBookingId(String tripId, String bookingId) {
        TripBookingModel tripBookingModels = tripBookingRepository.findByTripIdAndBookingId(tripId, bookingId);

        //dùng mapper
        TripBooking tripBooking = tripBookingMapper.toTripBooking(tripBookingModels);

        Booking booking = bookingMapper.toBooking(tripBookingModels.getBooking());

        tripBooking.setBooking(booking);

        return tripBooking;
    }

    //đếm số ghế được đặt theo trip booking id
    @Override
    public int countSeatsByTripBookingId(String tripBookingId) {
        return tripBookingRepository.countSeatsByTripBookingId(tripBookingId);
    }

    //tìm theo booking id
    @Override
    public List<TripBooking> findTripBookingsByBookingId(String bookingId) {
        List<TripBookingModel> tripBookingModels = tripBookingRepository.findByBookingId(bookingId);

        //dùng mapper
        List<TripBooking> tripBookings = tripBookingMapper.toTripBookings(tripBookingModels);

        return tripBookings;
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

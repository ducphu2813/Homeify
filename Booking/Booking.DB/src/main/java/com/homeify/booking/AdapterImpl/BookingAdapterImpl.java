package com.homeify.booking.AdapterImpl;

import com.homeify.booking.Adapter.BookingAdapter;
import com.homeify.booking.Entities.Booking;
import com.homeify.booking.Mapper.BookingMapper;
import com.homeify.booking.Model.BookingModel;
import com.homeify.booking.Repository.BookingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookingAdapterImpl implements BookingAdapter {

    private final BookingRepository bookingRepository;
    private final BookingMapper bookingMapper;

    public BookingAdapterImpl(BookingRepository bookingRepository, BookingMapper bookingMapper) {
        this.bookingRepository = bookingRepository;
        this.bookingMapper = bookingMapper;
    }

    @Override
    @Transactional
    public Booking addBooking(Booking booking) {
        //dùng mapper
        BookingModel bookingModel = bookingMapper.toBookingModel(booking);

        //set id mới cho booking
        String id = generateBookingId();
        bookingModel.setId(id);

        bookingRepository.save(bookingModel);

        BookingModel newBookingModel = bookingRepository.findById(id).orElse(null);

        return bookingMapper.toBooking(newBookingModel);
    }

    @Override
    @Transactional
    public Booking updateBooking(Booking booking, String id) {
        //tìm theo id
        BookingModel bookingModel = bookingRepository.findById(id).orElse(null);

        if (bookingModel == null) {
            return null;
        }

        //dùng mapper
        bookingModel = bookingMapper.toBookingModel(booking);

        //set lại id cũ
        bookingModel.setId(id);

        bookingModel = bookingRepository.save(bookingModel);

        return bookingMapper.toBooking(bookingModel);
    }

    @Override
    @Transactional
    public void deleteBooking(String bookingId) {
        bookingRepository.deleteById(bookingId);
    }

    //get all bookings
    @Override
    public List<Booking> getAllBookings() {
        List<BookingModel> bookingModel = bookingRepository.findAll();

        return bookingMapper.toBookings(bookingModel);
    }

    @Override
    public Booking findBookingById(String bookingId) {
        BookingModel bookingModel = bookingRepository.findById(bookingId).orElse(null);

        return bookingMapper.toBooking(bookingModel);
    }

    //tìm booking theo userId
    @Override
    public List<Booking> findBookingsByUserId(String userId) {
        List<BookingModel> bookingModels = bookingRepository.findByUserId(userId);

        return bookingMapper.toBookings(bookingModels);
    }

    //Tìm Booking theo userId và đã có chuyến đi tripId đó
    @Override
    public Booking findBookingByUserIdAndTripId(String userId, String tripId) {
        BookingModel bookingModel = bookingRepository.findBookingByUserIdAndTripId(userId, tripId);

        return bookingMapper.toBooking(bookingModel);
    }

    //lấy trip id và số ghế dã đặt theo booking id
    @Override
    public List<Object[]> findTripIdAndSeatCountByBookingId(String bookingId) {
        return bookingRepository.findTripIdAndSeatCountByBookingId(bookingId);
    }

    //tự động tạo id theo số thứ tự/tháng/năm(BK00001_10_2023, BK00002_10_2023, BK00003_10_2023,...)
    //tháng và năm là tháng và năm hiện tại
    //số thứ tự là số thứ tự của booking trong tháng đó
    private String generateBookingId() {
        //lấy tháng và năm hiện tại
        String month = String.valueOf(java.time.LocalDate.now().getMonthValue());
        String year = String.valueOf(java.time.LocalDate.now().getYear());

        //đổi month và year về int
        int monthInt = Integer.parseInt(month);
        int yearInt = Integer.parseInt(year);

        //lấy số thứ tự của booking trong tháng đó
        int count = bookingRepository.countByMonthAndYear(monthInt, yearInt);

        //in ra
        System.out.println("Số thứ tự booking trong tháng " + month + " năm " + year + " là: " + count);

        //và tăng số thứ tự lên 1
        int sequence = count + 1;

        // tạo id
        return "BK" + String.format("%05d", sequence) + "_" + month + "_" + year;
    }
}

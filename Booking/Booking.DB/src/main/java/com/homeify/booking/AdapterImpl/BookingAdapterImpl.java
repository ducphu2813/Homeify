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

        bookingModel = bookingRepository.save(bookingModel);

        return bookingMapper.toBooking(bookingModel);
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
}

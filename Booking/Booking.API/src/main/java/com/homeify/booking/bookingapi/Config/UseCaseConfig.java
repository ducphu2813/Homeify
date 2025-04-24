package com.homeify.booking.bookingapi.Config;

import com.homeify.booking.Adapter.BookingAdapter;
import com.homeify.booking.Adapter.SeatBookingAdapter;
import com.homeify.booking.Adapter.TripBookingAdapter;
import com.homeify.booking.AdapterImpl.BookingAdapterImpl;
import com.homeify.booking.AdapterImpl.SeatBookingAdapterImpl;
import com.homeify.booking.AdapterImpl.TripBookingAdapterImpl;
import com.homeify.booking.Mapper.BookingMapper;
import com.homeify.booking.Mapper.SeatBookingMapper;
import com.homeify.booking.Mapper.TripBookingMapper;
import com.homeify.booking.Repository.BookingRepository;
import com.homeify.booking.Repository.SeatBookingRepository;
import com.homeify.booking.Repository.TripBookingRepository;
import com.homeify.booking.Usecases.BookingUsecase;
import com.homeify.booking.Usecases.SeatBookingUsecase;
import com.homeify.booking.Usecases.TripBookingUsecase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {

    //đăng ký bean booking adapter
    @Bean
    public BookingAdapter bookingAdapter(BookingRepository bookingRepository
                                            , BookingMapper bookingMapper){
        return new BookingAdapterImpl(bookingRepository, bookingMapper);
    }

    //đăng ký bean trip booking adapter
    @Bean
    public TripBookingAdapter tripBookingAdapter(TripBookingRepository tripBookingRepository
                                            , TripBookingMapper tripBookingMapper
                                            , BookingRepository bookingRepository
                                            , BookingMapper bookingMapper){
        return new TripBookingAdapterImpl(tripBookingRepository, tripBookingMapper, bookingRepository, bookingMapper);
    }

    //dang ky bean seat booking adapter
    @Bean
    public SeatBookingAdapter seatBookingAdapter(SeatBookingRepository seatBookingRepository
                                            , SeatBookingMapper seatBookingMapper){
        return new SeatBookingAdapterImpl(seatBookingRepository, seatBookingMapper);
    }



    //---------------------
    ///--------------------
    ///--------------------

    //bean booking use case
    @Bean
    public BookingUsecase bookingUsecase (BookingAdapter bookingAdapter
                                            , TripBookingAdapter tripBookingAdapter
                                            , SeatBookingAdapter seatBookingAdapter){
        return new BookingUsecase(bookingAdapter, tripBookingAdapter, seatBookingAdapter);
    }

    //bean trip booking use case
    @Bean
    public TripBookingUsecase tripBookingUsecase (TripBookingAdapter tripBookingAdapter){
        return new TripBookingUsecase(tripBookingAdapter);
    }

    //bean seat booking use case
    @Bean
    public SeatBookingUsecase seatBookingUsecase (SeatBookingAdapter seatBookingAdapter){
        return new SeatBookingUsecase(seatBookingAdapter);
    }
}

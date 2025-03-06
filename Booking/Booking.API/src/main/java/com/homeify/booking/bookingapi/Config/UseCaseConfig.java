package com.homeify.booking.bookingapi.Config;

import com.homeify.booking.Adapter.BookingAdapter;
import com.homeify.booking.AdapterImpl.BookingAdapterImpl;
import com.homeify.booking.Mapper.BookingMapper;
import com.homeify.booking.Repository.BookingRepository;
import com.homeify.booking.Usecases.BookingUsecase;
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

    //---------------------
    ///--------------------
    ///--------------------

    //bean booking use case
    @Bean
    public BookingUsecase bookingUsecase (BookingAdapter bookingAdapter){
        return new BookingUsecase(bookingAdapter);
    }
}

package com.homeify.booking.bookingapi.Config;

import com.homeify.booking.Mapper.BookingMapper;
import com.homeify.booking.Mapper.SeatBookingMapper;
import com.homeify.booking.Mapper.TripBookingMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

    //đăng ký bean booking mapper
    @Bean
    public BookingMapper bookingMapper()
    {
        return Mappers.getMapper(BookingMapper.class);
    }

    //đăng ký bean trip booking mapper
    @Bean
    public TripBookingMapper tripBookingMapper()
    {
        return Mappers.getMapper(TripBookingMapper.class);
    }

    //đăng ký bean seat booking mapper
     @Bean
    public SeatBookingMapper seatBookingMapper()
    {
        return Mappers.getMapper(SeatBookingMapper.class);
    }
}

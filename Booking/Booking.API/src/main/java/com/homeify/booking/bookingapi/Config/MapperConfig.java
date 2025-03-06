package com.homeify.booking.bookingapi.Config;

import com.homeify.booking.Mapper.BookingMapper;
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
}

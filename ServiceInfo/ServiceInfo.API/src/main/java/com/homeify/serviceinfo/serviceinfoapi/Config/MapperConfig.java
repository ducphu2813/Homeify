package com.homeify.serviceinfo.serviceinfoapi.Config;

import com.homeify.serviceinfo.Mapper.*;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

    //đăng ký bean city mapper
    @Bean
    public CityMapper cityMapper()
    {
        return Mappers.getMapper(CityMapper.class);
    }

    //đăng ký bean transportation mapper
    @Bean
    public TransportationMapper transportationMapper()
    {
        return Mappers.getMapper(TransportationMapper.class);
    }

    //đăng ký bean seat mapper
    @Bean
    public SeatMapper seatMapper()
    {
        return Mappers.getMapper(SeatMapper.class);
    }

    //đăng ký bean trip info mapper
    @Bean
    public TripInfoMapper tripInfoMapper()
    {
        return Mappers.getMapper(TripInfoMapper.class);
    }

    //đăng ký bean pickup area mapper
    @Bean
    public PickupAreaMapper pickupAreaMapper()
    {
        return Mappers.getMapper(PickupAreaMapper.class);
    }

    //đăng ký bean trip mapper
    @Bean
    public TripMapper tripMapper()
    {
        return Mappers.getMapper(TripMapper.class);
    }
}

package com.homeify.serviceinfo.serviceinfoapi.Config;

import com.homeify.serviceinfo.Adapter.*;
import com.homeify.serviceinfo.AdapterImpl.*;
import com.homeify.serviceinfo.Mapper.*;
import com.homeify.serviceinfo.Repository.*;
import com.homeify.serviceinfo.UseCases.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCasesConfig {

    //đăng ký adapter

    //adapter của city
    @Bean
    public CityAdapter cityAdapter (CityRepository cityRepository
                                    , CityMapper cityMapper)
    {
        return new CityAdapterImpl(cityRepository, cityMapper);
    }

    //adapter của transportation
     @Bean
    public TransportationAdapter transportationAdapter (TransportationRepository transportationRepository
                                    , TransportationMapper transportationMapper)
    {
        return new TransportationAdapterImpl(transportationRepository, transportationMapper);
    }

    //adatper của seat
    @Bean
    public SeatAdapter seatAdapter (SeatRepository seatRepository
                                    , SeatMapper seatMapper
                                    , TransportationRepository transportationRepository
                                    , TransportationMapper transportationMapper)
    {
        return new SeatAdapterImpl(seatRepository, seatMapper, transportationRepository, transportationMapper);
    }

    //adapter của trip info
    @Bean
    public TripInfoAdapter tripInfoAdapter (TripInfoRepository tripInfoRepository
                                    , TripInfoMapper tripInfoMapper
                                    , CityRepository cityRepository
                                    , CityMapper cityMapper)
    {
        return new TripInfoAdapterImpl(tripInfoRepository, tripInfoMapper, cityRepository, cityMapper);
    }

    //adapter của pickup area
    @Bean
    public PickupAreaAdapter pickupAreaAdapter (PickupAreaRepository pickupAreaRepository
                                    , PickupAreaMapper pickupAreaMapper
                                    , CityRepository cityRepository
                                    , CityMapper cityMapper)
    {
        return new PickupAreaAdapterImpl(pickupAreaRepository, pickupAreaMapper, cityRepository, cityMapper);
    }

    //adapter của trip
    @Bean
    public TripAdapter tripAdapter (TripRepository tripRepository
                                    , TripMapper tripMapper
                                    , TripInfoRepository tripInfoRepository
                                    , TripInfoMapper tripInfoMapper
                                    , TransportationRepository transportationRepository
                                    , TransportationMapper transportationMapper
                                    , PickupAreaRepository pickupAreaRepository
                                    , PickupAreaMapper pickupAreaMapper
                                    , CityRepository cityRepository
                                    , CityMapper cityMapper
                                    , SeatRepository seatRepository
                                    , SeatMapper seatMapper)
    {
        return new TripAdapterImpl(tripRepository
                                    , tripMapper
                                    , tripInfoRepository
                                    , tripInfoMapper
                                    , transportationRepository
                                    , transportationMapper
                                    , pickupAreaRepository
                                    , pickupAreaMapper
                                    , cityRepository
                                    , cityMapper
                                    , seatRepository
                                    , seatMapper);
    }

    //kafka adapter
//    @Bean
//    public KafkaEventConsumerAdapter kafkaEventConsumerAdapter (KafkaEventConsumerAdapterImpl kafkaEventConsumerAdapterImpl){
//        return kafkaEventConsumerAdapterImpl;
//    }



    //đăng ký use case

    //use case của city
    @Bean
    public CityUsecase cityUsecase (CityAdapter cityAdapter){
        return new CityUsecase(cityAdapter);
    }

    //usecase của transport
    @Bean
    public TransportationUsecase transportationUsecase (TransportationAdapter transportationAdapter){
        return new TransportationUsecase(transportationAdapter);
    }

    //usecase của seat
    @Bean
    public SeatUsecase seatUsecase (SeatAdapter seatAdapter){
        return new SeatUsecase(seatAdapter);
    }

    //usecase của trip info
    @Bean
    public TripInfoUsecase tripInfoUsecase (TripInfoAdapter tripInfoAdapter){
        return new TripInfoUsecase(tripInfoAdapter);
    }

    //usecase của pickup area
    @Bean
    public PickupAreaUsecase pickupAreaUsecase (PickupAreaAdapter pickupAreaAdapter){
        return new PickupAreaUsecase(pickupAreaAdapter);
    }

    //usecase của trip
    @Bean
    public TripUsecase tripUsecase (TripAdapter tripAdapter){
        return new TripUsecase(tripAdapter);
    }
}

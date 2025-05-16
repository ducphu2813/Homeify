package com.homeify.booking.bookingapi.Config;

import com.homeify.booking.Adapter.*;
import com.homeify.booking.AdapterImpl.*;
import com.homeify.booking.Mapper.BookingMapper;
import com.homeify.booking.Mapper.SeatBookingMapper;
import com.homeify.booking.Mapper.TripBookingMapper;
import com.homeify.booking.Repository.BookingRepository;
import com.homeify.booking.Repository.SeatBookingRepository;
import com.homeify.booking.Repository.TripBookingRepository;
import com.homeify.booking.Usecases.BookingUsecase;
import com.homeify.booking.Usecases.PaymentUsecases;
import com.homeify.booking.Usecases.SeatBookingUsecase;
import com.homeify.booking.Usecases.TripBookingUsecase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;

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

    //bean payment adapter
    @Bean
    public VNPayAdapter vnpayAdapter(BookingRepository bookingRepository) {
        return new VNPayAdapterImpl(bookingRepository);
    }

    //dang ky kafka producer adapter
     @Bean
    public BookingKafkaProducer bookingKafkaProducer(BookingKafkaProducerImpl bookingKafkaProducerImpl) {
        return bookingKafkaProducerImpl;
    }

    //dang ky kafka consumer adapter
    @Bean
    public BookingKafkaConsumer bookingKafkaConsumer(BookingKafkaConsumerImpl bookingKafkaConsumerImpl) {
        return bookingKafkaConsumerImpl;
    }


    //---------------------
    ///--------------------
    ///--------------------

    //bean booking use case
    @Bean
    public BookingUsecase bookingUsecase (BookingAdapter bookingAdapter
                                            , TripBookingAdapter tripBookingAdapter
                                            , SeatBookingAdapter seatBookingAdapter
                                            , BookingKafkaProducer bookingKafkaProducer
    ){
        return new BookingUsecase(bookingAdapter, tripBookingAdapter, seatBookingAdapter, bookingKafkaProducer);
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

    //bean payment use case
    @Bean
    public PaymentUsecases paymentUsecases(VNPayAdapter vnpayAdapter) {
        return new PaymentUsecases(vnpayAdapter);
    }
}

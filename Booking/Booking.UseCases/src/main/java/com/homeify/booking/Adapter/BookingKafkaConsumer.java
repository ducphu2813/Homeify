package com.homeify.booking.Adapter;

import com.homeify.booking.Event.BookingExpireEvent;

import java.time.LocalDateTime;

public interface BookingKafkaConsumer {

    public void consume(BookingExpireEvent event);
}

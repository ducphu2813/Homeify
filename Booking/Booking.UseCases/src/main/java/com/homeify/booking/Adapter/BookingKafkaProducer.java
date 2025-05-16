package com.homeify.booking.Adapter;

import com.homeify.booking.Entities.Booking;

public interface BookingKafkaProducer {

    public void sendBookingExpireEvent(Booking booking);

    public void sendDeleteBookingEvent(String tripId, int seatCount);
}

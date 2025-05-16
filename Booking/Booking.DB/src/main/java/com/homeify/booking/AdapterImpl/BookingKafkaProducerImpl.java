package com.homeify.booking.AdapterImpl;

import com.homeify.booking.Adapter.BookingKafkaProducer;
import com.homeify.booking.Entities.Booking;
import com.homeify.booking.Event.BookingExpireEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class BookingKafkaProducerImpl implements BookingKafkaProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public BookingKafkaProducerImpl(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void sendBookingExpireEvent(Booking booking) {

        BookingExpireEvent bookingExpireEvent = new BookingExpireEvent();

        bookingExpireEvent.setBookingId(booking.getId());
        bookingExpireEvent.setCreatedAt(booking.getCreatedAt());

        //gửi bookingExpireEvent lên kafka
        try {
            kafkaTemplate.send("booking-expire-topic", bookingExpireEvent);
            System.out.println("Sent message to topic booking-expire-topic: " + bookingExpireEvent);
        } catch (Exception e) {
            throw new RuntimeException("Failed to send Kafka message", e);
        }
    }

    @Override
    public void sendDeleteBookingEvent(String tripId, int seatCount) {
        // Gửi dữ liệu vào topic
        try {
            kafkaTemplate.send("booking-delete-topic", tripId, seatCount);
            System.out.println("Sent message to topic booking-delete-topic: " + tripId + ", " + seatCount);
        } catch (Exception e) {
            throw new RuntimeException("Failed to send Kafka message", e);
        }
    }


}

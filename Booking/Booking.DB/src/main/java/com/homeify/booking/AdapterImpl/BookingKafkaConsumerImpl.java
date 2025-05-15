package com.homeify.booking.AdapterImpl;

import com.homeify.booking.Adapter.BookingKafkaConsumer;
import com.homeify.booking.Event.BookingExpireEvent;
import com.homeify.booking.Repository.BookingRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;

import java.time.Duration;
import java.time.LocalDateTime;

public class BookingKafkaConsumerImpl implements BookingKafkaConsumer {

    private final BookingRepository bookingRepository;

    public BookingKafkaConsumerImpl(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @Override
    @KafkaListener(topics = "booking-expire", groupId = "booking-group")
    public void consume(BookingExpireEvent event) {

        String bookingId = event.getBookingId();
        LocalDateTime createdAt = event.getCreatedAt();

        // Đợi đúng 5 phút
        if (createdAt.plusMinutes(5).isAfter(LocalDateTime.now())) {
            // Nếu chưa đủ 5 phút thì tiếp tục delay
            try {
                long delayMillis = Duration.between(LocalDateTime.now(), createdAt.plusMinutes(5)).toMillis();
                Thread.sleep(delayMillis);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        bookingRepository.findById(bookingId).ifPresent(booking -> {
            if ("PENDING".equals(booking.getPaymentStatus())) {
                bookingRepository.deleteById(bookingId);
                System.out.println("Booking đã được xóa: " + bookingId);
            }
        });
    }
}

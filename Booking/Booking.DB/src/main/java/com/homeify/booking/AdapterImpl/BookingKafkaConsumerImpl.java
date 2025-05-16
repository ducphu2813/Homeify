package com.homeify.booking.AdapterImpl;

import com.homeify.booking.Adapter.BookingKafkaConsumer;
import com.homeify.booking.Event.BookingExpireEvent;
import com.homeify.booking.Repository.BookingRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class BookingKafkaConsumerImpl implements BookingKafkaConsumer {

    private final BookingRepository bookingRepository;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public BookingKafkaConsumerImpl(BookingRepository bookingRepository, KafkaTemplate<String, Object> kafkaTemplate) {
        this.bookingRepository = bookingRepository;
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    @KafkaListener(topics = "booking-expire-topic", groupId = "booking-group")
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

                //trong này sẽ làm thêm 1 phần nữa đó là gửi dữ liệu vào topic cho bên trip service dể cập nhật lại số ghế trống
//                List<Object[]> results = bookingRepository.findTripIdAndSeatCountByBookingId(bookingId);
//
//                for (Object[] result : results) {
//                    String tripId = (String) result[0];
//                    int seatCount = (int) result[1];
//
//                    // Gửi dữ liệu vào topic
//                    try {
//                        kafkaTemplate.send("booking-delete-topic", tripId, seatCount);
//                        System.out.println("Sent message to topic booking-delete-topic: " + tripId + ", " + seatCount);
//                    } catch (Exception e) {
//                        throw new RuntimeException("Failed to send Kafka message", e);
//                    }
//                }

                bookingRepository.deleteById(bookingId);
                System.out.println("Booking đã được xóa: " + bookingId);


            }
        });
    }
}

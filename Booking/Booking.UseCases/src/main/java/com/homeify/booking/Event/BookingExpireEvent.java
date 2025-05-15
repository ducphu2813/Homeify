package com.homeify.booking.Event;

import java.time.LocalDateTime;

public class BookingExpireEvent {

    private String bookingId;
    private LocalDateTime createdAt;

    //constructor
    public BookingExpireEvent(String bookingId, LocalDateTime createdAt) {
        this.bookingId = bookingId;
        this.createdAt = createdAt;
    }

    //getter and setter methods
    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}

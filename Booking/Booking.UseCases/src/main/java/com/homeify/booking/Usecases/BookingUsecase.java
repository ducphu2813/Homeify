package com.homeify.booking.Usecases;

import com.homeify.booking.Adapter.BookingAdapter;
import com.homeify.booking.Adapter.BookingKafkaProducer;
import com.homeify.booking.Adapter.SeatBookingAdapter;
import com.homeify.booking.Adapter.TripBookingAdapter;
import com.homeify.booking.Entities.Booking;
import com.homeify.booking.Entities.SeatBooking;
import com.homeify.booking.Entities.TripBooking;
import com.homeify.booking.Event.BookingExpireEvent;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class BookingUsecase {

    private final BookingAdapter bookingAdapter;

    private final TripBookingAdapter tripBookingAdapter;

    private final SeatBookingAdapter seatBookingAdapter;

    private final BookingKafkaProducer bookingKafkaProducer;

    public BookingUsecase(BookingAdapter bookingAdapter
                            , TripBookingAdapter tripBookingAdapter
                            , SeatBookingAdapter seatBookingAdapter
                            , BookingKafkaProducer bookingKafkaProducer
    ) {
        this.bookingAdapter = bookingAdapter;
        this.tripBookingAdapter = tripBookingAdapter;
        this.seatBookingAdapter = seatBookingAdapter;
        this.bookingKafkaProducer = bookingKafkaProducer;
    }

    //teh6m booking
    public Booking addBooking(Booking booking) {
        return bookingAdapter.addBooking(booking);
    }

    //sửa booking
    public Booking updateBooking(Booking booking, String id) {
        return bookingAdapter.updateBooking(booking, id);
    }

    //xóa booking
    public void deleteBooking(String bookingId) {

        // lấy thông tin booking để gửi sự kiện
//        List<Object[]> object = bookingAdapter.findTripIdAndSeatCountByBookingId(bookingId);
//
//        String tripId = (String) object.get(0)[0];
//        int seatCount = (int) object.get(0)[1];
//
//        // gửi sự kiện xóa booking
//        try {
//            bookingKafkaProducer.sendDeleteBookingEvent(tripId, seatCount);
//            System.out.println("Sent message to topic booking-delete-topic: " + tripId + ", " + seatCount);
//        } catch (Exception e) {
//            throw new RuntimeException("Failed to send Kafka message", e);
//        }

        bookingAdapter.deleteBooking(bookingId);
    }

    //lấy tat ca booking
    public List<Booking> getAllBookings() {
        return bookingAdapter.getAllBookings();
    }

    //tìm theo id
    public Booking findBookingById(String bookingId) {
        return bookingAdapter.findBookingById(bookingId);
    }

    //tìm theo userId
    public List<Booking> findBookingsByUserId(String userId) {
        return bookingAdapter.findBookingsByUserId(userId);
    }

    //đặt vé
    public Booking bookTrip(String tripId, String userId, List<String> seatIds, Long seatPrice) {

        //kiểm tra người dùng đã có đặt chuyến này chưa
        Booking booking = bookingAdapter.findBookingByUserIdAndTripId(userId, tripId);
        TripBooking tripBooking;

        //nếu đã đặt chuyến này thì tìm đúng cái TripBooking đó
        if(booking != null) {
            tripBooking = tripBookingAdapter.findTripBookingsByTripIdAndBookingId(tripId, booking.getId());
        }
        else{
            //chưa có thì tạo mới booking và tripBooking
            booking = new Booking();
            booking.setUserId(userId);
            booking.setCreatedAt(LocalDateTime.now());
            booking.setBookingDate(LocalDateTime.now());
            booking.setStatus("Đã đặt");
            booking.setPaymentStatus("PENDING");
            booking.setTotalAmount(0L);
            booking = bookingAdapter.addBooking(booking);

            //gửi bookingExpireEvent lên kafka
            try {
                bookingKafkaProducer.sendBookingExpireEvent(booking);
                System.out.println("Sent message to topic booking-expire-topic with booking id: " + booking.getId());
            } catch (Exception e) {
                throw new RuntimeException("Failed to send Kafka message", e);
            }

            tripBooking = new TripBooking();
            tripBooking.setBooking(booking);
            tripBooking.setTripId(tripId);
            tripBooking.setAmount(0L);
            tripBooking = tripBookingAdapter.addTripBooking(tripBooking);
        }

        //kiếm tra ghế ngồi đã được đặt chưa
        List<TripBooking> bookedTrips = tripBookingAdapter.findTripBookingsByTripId(tripId);
        List<String> tripBookingIds = bookedTrips.stream()
                .map(TripBooking::getId)
                .toList();

        //lấy danh sách ghế đã đặt
        List<SeatBooking> bookedSeats = seatBookingAdapter.findExistingBookedSeats(tripBookingIds, seatIds);

        if(!bookedSeats.isEmpty()) {
            //nếu có ghế đã đặt thì trả về null
            throw new RuntimeException("Một số ghế đã được đặt. Vui lòng chọn lại.");
        }

        //tạo danh sách ghế ngồi
        List<SeatBooking> seatBookings = new ArrayList<>();
        for (String seatId : seatIds) {
            SeatBooking seatBooking = new SeatBooking();
            seatBooking.setTripBooking(tripBooking);
            seatBooking.setSeatId(seatId);
            seatBookings.add(seatBookingAdapter.addSeatBooking(seatBooking));
        }

        //sau bước này có nghĩa là ta đã tìm được đúng tripBooking và booking
        //thì có thể cập nhật amount và total amount cho tripBooking và booking
        //cập nhật tiền của tripBooking
        int tripBookingSeats = tripBookingAdapter.countSeatsByTripBookingId(tripBooking.getId());
        tripBooking.setAmount(tripBookingSeats * seatPrice);
        tripBookingAdapter.updateTripBooking(tripBooking, tripBooking.getId());

        //cập nhật tiền của booking
        List<TripBooking> tripBookings = tripBookingAdapter.findTripBookingsByBookingId(booking.getId());
        Long totalAmount = tripBookings.stream()
                .map(TripBooking::getAmount)
                .reduce(0L, Long::sum);
        booking.setTotalAmount(totalAmount);
        bookingAdapter.updateBooking(booking, booking.getId());

        return bookingAdapter.findBookingById(booking.getId());
    }

    //xóa ghế khỏi booking
    public Booking removeSeatsFromTrip(String tripId, String userId, List<String> seatIds, Long seatPrice)
    {
        // Tìm Booking và TripBooking
        Booking booking = bookingAdapter.findBookingByUserIdAndTripId(userId, tripId);
        if (booking == null) {
            throw new RuntimeException("Không tìm thấy booking cho người dùng và chuyến đi này.");
        }

        TripBooking tripBooking = tripBookingAdapter.findTripBookingsByTripIdAndBookingId(tripId, booking.getId());
        if (tripBooking == null) {
            throw new RuntimeException("Không tìm thấy trip booking cho chuyến đi.");
        }

        // Lấy danh sách SeatBooking tương ứng cần xóa
        List<SeatBooking> seatsToDelete = seatBookingAdapter.findByTripBookingIdAndSeatId(tripBooking.getId(), seatIds);
        if (seatsToDelete.isEmpty()) {
            throw new RuntimeException("Không tìm thấy ghế cần xóa.");
        }

        // Thực hiện xóa ghế
        for (SeatBooking seat : seatsToDelete) {
            seatBookingAdapter.deleteSeatBooking(seat.getId());
        }

        // Sau khi xóa, có thể cập nhật lại amount và totalAmount cho TripBooking và Booking
        //cập nhật tiền của tripBooking
        int tripBookingSeats = tripBookingAdapter.countSeatsByTripBookingId(tripBooking.getId());
        tripBooking.setAmount(tripBookingSeats * seatPrice);
        tripBookingAdapter.updateTripBooking(tripBooking, tripBooking.getId());

        //cập nhật tiền của booking
        List<TripBooking> tripBookings = tripBookingAdapter.findTripBookingsByBookingId(booking.getId());
        Long totalAmount = tripBookings.stream()
                .map(TripBooking::getAmount)
                .reduce(0L, Long::sum);
        booking.setTotalAmount(totalAmount);
        bookingAdapter.updateBooking(booking, booking.getId());

        // Nếu không còn ghế nào => xóa luôn TripBooking? (tuỳ yêu cầu)
//        List<SeatBooking> remainingSeats = seatBookingAdapter.findSeatBookingsByTripBookingId(tripBooking.getId());
//        if (remainingSeats.isEmpty()) {
//            tripBookingAdapter.deleteTripBooking(tripBooking.getId());
//
//            // Nếu không còn tripBooking nào => xóa luôn Booking?
//            List<TripBooking> remainingTrips = tripBookingAdapter.findTripBookingsByBookingId(booking.getId());
//            if (remainingTrips.isEmpty()) {
//                bookingAdapter.deleteBooking(booking.getId());
//                return null;
//            }
//        }

        return bookingAdapter.findBookingById(booking.getId());
    }
}

package com.homeify.booking.bookingapi.Controller;


import com.homeify.booking.Entities.Booking;
import com.homeify.booking.Usecases.BookingUsecase;
import com.homeify.booking.bookingapi.DTO.BookingDTO;
import com.homeify.booking.bookingapi.DTO.CreateBookingDTO;
import com.homeify.booking.bookingapi.DTO.SaveBookingDTO;
import com.homeify.booking.bookingapi.Mapper.BookingDTOMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/booking")
public class BookingController {

    private final BookingUsecase bookingUsecase;
    private final BookingDTOMapper bookingDTOMapper;

    public BookingController(BookingUsecase bookingUsecase, BookingDTOMapper bookingDTOMapper) {
        this.bookingUsecase = bookingUsecase;
        this.bookingDTOMapper = bookingDTOMapper;
    }

    //get all
    @GetMapping("/getAll")
    public List<BookingDTO> getAll() {

        List<Booking> bookings = bookingUsecase.getAllBookings();

        //chuyển từ List<Booking> sang List<BookingDTO>
        //dùng mapper
        List<BookingDTO> bookingDTOs = bookingDTOMapper.toBookingDTOs(bookings);

        return bookingDTOs;
    }

    //thêm
    @PostMapping("/add")
    public BookingDTO add(@RequestBody SaveBookingDTO bookingDTO) {

        Booking b = bookingDTOMapper.toBooking(bookingDTO);

        b = bookingUsecase.addBooking(b);

        return bookingDTOMapper.toBookingDTO(b);

    }

    //sửa
    @PutMapping("/update/{id}")
    public BookingDTO update(@RequestBody SaveBookingDTO bookingDTO, @PathVariable String id) {

        Booking b = bookingDTOMapper.toBooking(bookingDTO);

        b = bookingUsecase.updateBooking(b, id);

        return bookingDTOMapper.toBookingDTO(b);
    }

    //xóa
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable String id) {
        bookingUsecase.deleteBooking(id);
    }

    //tìm theo id
    @GetMapping("/getById/{id}")
    public BookingDTO getById(@PathVariable String id) {

        Booking b = bookingUsecase.findBookingById(id);
        if (b == null) {
            return null;
        }

        //lấy trip id từ trip booking
        String tripId = b.getTripBookings().get(0).getTripId();

        return bookingDTOMapper.toBookingDTO(b);
    }

    //API đặt vé
    @PostMapping("/book")
    public ResponseEntity<?> book(@RequestBody CreateBookingDTO bookingDTO) {

        try {
            Booking booking = bookingUsecase.bookTrip(bookingDTO.getTripId(), bookingDTO.getUserId(), bookingDTO.getSeatId());

            return new ResponseEntity<>(bookingDTOMapper.toBookingDTO(booking), HttpStatus.OK);
        }
        catch (Exception e) {
            e.printStackTrace();
            //trả bad request
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    //xóa ghế khỏi vé
    @PostMapping("/deleteSeat")
    public ResponseEntity<?> deleteSeat(@RequestBody CreateBookingDTO bookingDTO) {
        try {
            Booking booking = bookingUsecase.removeSeatsFromTrip(bookingDTO.getTripId(), bookingDTO.getUserId(), bookingDTO.getSeatId());
            return new ResponseEntity<>(bookingDTOMapper.toBookingDTO(booking), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            //trả bad request
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}

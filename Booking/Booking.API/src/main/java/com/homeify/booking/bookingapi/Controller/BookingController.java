package com.homeify.booking.bookingapi.Controller;


import com.homeify.booking.Entities.Booking;
import com.homeify.booking.Usecases.BookingUsecase;
import com.homeify.booking.bookingapi.DTO.BookingDTO;
import com.homeify.booking.bookingapi.DTO.CreateBookingDTO;
import com.homeify.booking.bookingapi.DTO.SaveBookingDTO;
import com.homeify.booking.bookingapi.FeignClient.TripClientInterface;
import com.homeify.booking.bookingapi.Mapper.BookingDTOMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/booking")
public class BookingController {

    private final BookingUsecase bookingUsecase;
    private final BookingDTOMapper bookingDTOMapper;

    //feign client của Trip Service
    private final TripClientInterface tripClientInterface;

    public BookingController(BookingUsecase bookingUsecase, BookingDTOMapper bookingDTOMapper, TripClientInterface tripClientInterface) {
        this.bookingUsecase = bookingUsecase;
        this.bookingDTOMapper = bookingDTOMapper;
        this.tripClientInterface = tripClientInterface;
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

    //tìm theo userId
    @GetMapping("/getByUserId/{userId}")
    public List<BookingDTO> getByUserId(@PathVariable String userId) {

        List<Booking> bookings = bookingUsecase.findBookingsByUserId(userId);

        //chuyển từ List<Booking> sang List<BookingDTO>
        //dùng mapper
        List<BookingDTO> bookingDTOs = bookingDTOMapper.toBookingDTOs(bookings);

        return bookingDTOs;
    }

    //API đặt vé
    @PostMapping("/book")
    public ResponseEntity<?> book(@RequestBody CreateBookingDTO bookingDTO) {

        try {
            //đầu tiên thì cần lấy trip id gọi qua Trip Service để kiểm tra số ghế còn trống
            int availableSeats = tripClientInterface.getAvailableSeats(bookingDTO.getTripId());

            if(availableSeats < bookingDTO.getSeatId().size()) {
                //nếu số ghế còn trống < số ghế đã đặt thì trả về bad request
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            Booking booking = bookingUsecase.bookTrip(bookingDTO.getTripId(), bookingDTO.getUserId(), bookingDTO.getSeatId(), bookingDTO.getSeatPrice());

            //book xong thì lấy số lượng ghế đã đặt đưa cho Trip Service để cập nhật số ghế còn trống
            //lấy số ghế đã đặt(đếm qua từng TripBooking trong booking)
            int bookedSeats = bookingDTO.getSeatId().size();

            //in thử
            System.out.println("Số ghế đã đặt: " + bookedSeats);
            System.out.println("trip id: " + bookingDTO.getTripId());

            //lấy 2 cái này đưa cho Trip Service để cập nhật số ghế còn trống
            //gộp vào thành 1 Map
            Map<String, Object> map = new HashMap<>();
            map.put("bookedSeats", bookedSeats);
            map.put("tripId", bookingDTO.getTripId());
            map.put("action", "book");

            //gọi trip client để cập nhật số ghế còn trống
            tripClientInterface.updateBookedSeats(map);

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
            Booking booking = bookingUsecase.removeSeatsFromTrip(bookingDTO.getTripId(), bookingDTO.getUserId(), bookingDTO.getSeatId(), bookingDTO.getSeatPrice());

            //xóa ghế xong thì lấy số ghế xóa đưa cho Trip Service để cập nhật số ghế còn trống

            //cái này thì chỉ cần đếm số ghế trong bookingDTO
            int bookedSeats = bookingDTO.getSeatId().size();

            //in thử
            System.out.println("Số ghế đã xóa: " + bookedSeats);
            System.out.println("trip id: " + bookingDTO.getTripId());

            //lấy 2 cái này đưa cho Trip Service để cập nhật số ghế còn trống
            //gộp vào thành 1 Map
            Map<String, Object> map = new HashMap<>();
            map.put("bookedSeats", bookedSeats);
            map.put("tripId", bookingDTO.getTripId());
            map.put("action", "delete");

            //gọi trip client để cập nhật số ghế còn trống
            tripClientInterface.updateBookedSeats(map);

            return new ResponseEntity<>(bookingDTOMapper.toBookingDTO(booking), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            //trả bad request
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }


    }
}

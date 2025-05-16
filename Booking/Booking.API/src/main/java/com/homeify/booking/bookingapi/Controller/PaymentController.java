package com.homeify.booking.bookingapi.Controller;


import com.homeify.booking.Usecases.PaymentUsecases;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    private final PaymentUsecases paymentUsecase;

    public PaymentController(PaymentUsecases paymentUsecase) {
        this.paymentUsecase = paymentUsecase;
    }

    //tạo url thanh toán
    @GetMapping("/createOrder")
    public String createOrder(@RequestParam("amount") int orderTotal,
                              @RequestParam("orderInfo") String orderInfo) {

        String urlReturn = "http://localhost:5173";

        return paymentUsecase.createOrder(orderTotal, orderInfo, urlReturn);
    }

    //trả về mã giao dịch
    @GetMapping("/orderReturn")
    public int orderReturn(HttpServletRequest request) {
        return paymentUsecase.orderReturn(request);
    }
}

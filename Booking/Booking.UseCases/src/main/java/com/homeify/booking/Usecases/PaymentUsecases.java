package com.homeify.booking.Usecases;


import com.homeify.booking.Adapter.VNPayAdapter;
import jakarta.servlet.http.HttpServletRequest;

public class PaymentUsecases {

    private final VNPayAdapter vnPayAdapter;

    public PaymentUsecases(VNPayAdapter vnPayAdapter) {
        this.vnPayAdapter = vnPayAdapter;
    }

    //tạo url thanh toán
    public String createOrder(int total, String orderInfor, String urlReturn) {
        return vnPayAdapter.createOrder(total, orderInfor, urlReturn);
    }

    //trả về mã giao dịch
    public int orderReturn(HttpServletRequest request) {
        int paymentStatus = vnPayAdapter.orderReturn(request);

        //in thử:
        System.out.println("Trạng thái giao dịch: " + paymentStatus);



        return paymentStatus;
    }

}

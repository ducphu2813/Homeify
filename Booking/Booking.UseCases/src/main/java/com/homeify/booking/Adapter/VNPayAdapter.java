package com.homeify.booking.Adapter;

import jakarta.servlet.http.HttpServletRequest;

public interface VNPayAdapter {

    public String createOrder(int total, String orderInfor, String urlReturn);
    public int orderReturn(HttpServletRequest request);
}

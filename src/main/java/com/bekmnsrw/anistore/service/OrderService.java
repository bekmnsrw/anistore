package com.bekmnsrw.anistore.service;

import com.bekmnsrw.anistore.dto.form.OrderForm;
import com.bekmnsrw.anistore.dto.form.OrderHistoryDto;

import java.util.List;

public interface OrderService {

    Double getTotalOrderPrice(String email);
    void makeOrder(OrderForm orderForm, String email);
    List<OrderHistoryDto> getOrderHistory(String email);
}

package com.bekmnsrw.anistore.service;

import com.bekmnsrw.anistore.dto.form.OrderForm;
import com.bekmnsrw.anistore.dto.form.OrderHistoryDto;
import com.bekmnsrw.anistore.dto.order.OrderDto;
import com.bekmnsrw.anistore.dto.order.OrderPage;

import java.util.List;

public interface OrderService {

    Double getTotalOrderPrice(String email);
    void makeOrder(OrderForm orderForm, String email);
    List<OrderHistoryDto> getOrderHistory(String email);
    OrderPage getAll(Integer page);
    OrderDto updateOrderStatus(Long orderId, String status);
}

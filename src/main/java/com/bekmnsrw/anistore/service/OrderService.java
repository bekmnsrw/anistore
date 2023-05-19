package com.bekmnsrw.anistore.service;

import com.bekmnsrw.anistore.dto.form.OrderForm;

public interface OrderService {

    Double getTotalOrderPrice(String email);
    void makeOrder(OrderForm orderForm, String email);
}

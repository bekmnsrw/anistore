package com.bekmnsrw.anistore.mapper;

import com.bekmnsrw.anistore.dto.order.OrderDto;
import com.bekmnsrw.anistore.model.Order;

import java.util.List;

public interface OrderMapper {

    OrderDto from(Order order);
    List<OrderDto> from(List<Order> orders);
}

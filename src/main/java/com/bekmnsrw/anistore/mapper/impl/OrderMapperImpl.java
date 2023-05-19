package com.bekmnsrw.anistore.mapper.impl;

import com.bekmnsrw.anistore.dto.OrderDto;
import com.bekmnsrw.anistore.mapper.OrderMapper;
import com.bekmnsrw.anistore.model.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderMapperImpl implements OrderMapper {

    @Override
    public OrderDto from(Order order) {
        return OrderDto.builder()
                .id(order.getId())
                .createdAt(order.getCreatedAt())
                .deliveryAddress(order.getDeliveryAddress())
                .orderStatus(order.getOrderStatus())
                .totalOrderPrice(order.getTotalOrderPrice())
                .build();
    }
}

package com.bekmnsrw.anistore.mapper.impl;

import com.bekmnsrw.anistore.dto.order.OrderDto;
import com.bekmnsrw.anistore.mapper.OrderMapper;
import com.bekmnsrw.anistore.model.Order;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public List<OrderDto> from(List<Order> orders) {
        return orders.stream()
                .map(this::from)
                .collect(Collectors.toList());
    }
}

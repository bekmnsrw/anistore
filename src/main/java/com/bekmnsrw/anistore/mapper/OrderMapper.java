package com.bekmnsrw.anistore.mapper;

import com.bekmnsrw.anistore.dto.OrderDto;
import com.bekmnsrw.anistore.model.Order;

public interface OrderMapper {

    OrderDto from(Order order);
}

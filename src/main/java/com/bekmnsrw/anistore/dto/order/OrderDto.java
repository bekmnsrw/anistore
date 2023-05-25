package com.bekmnsrw.anistore.dto.order;

import com.bekmnsrw.anistore.model.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
    private Long id;
    private String createdAt;
    private String deliveryAddress;
    private OrderStatus orderStatus;
    private Double totalOrderPrice;
}

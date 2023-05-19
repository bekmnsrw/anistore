package com.bekmnsrw.anistore.dto.form;

import com.bekmnsrw.anistore.model.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderHistoryDto {
    private String createdAt;
    private List<String> products;
    private Double price;
    private String address;
    private OrderStatus status;
}

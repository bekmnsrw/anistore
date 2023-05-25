package com.bekmnsrw.anistore.dto.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderPage {
    private List<OrderDto> orders;
    private Integer totalPages;
}

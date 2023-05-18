package com.bekmnsrw.anistore.dto;

import com.bekmnsrw.anistore.model.enums.ProductCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartItemDto {
    private Long productId;
    private String productTitle;
    private String productImageUrl;
    private Double productPrice;
    private ProductCategory productCategory;
    private Long productAmount;
}

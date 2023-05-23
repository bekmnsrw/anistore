package com.bekmnsrw.anistore.dto.product;

import com.bekmnsrw.anistore.model.enums.ProductCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private Long id;
    private String title;
    private String description;
    private String imageUrl;
    private Double price;
    private ProductCategory productCategory;
}

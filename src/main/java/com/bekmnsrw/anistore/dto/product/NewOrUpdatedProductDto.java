package com.bekmnsrw.anistore.dto.product;

import com.bekmnsrw.anistore.model.enums.ProductCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NewOrUpdatedProductDto {
    private String title;
    private String description;
    private String imageUrl;
    private Double price;
    private ProductCategory category;
}

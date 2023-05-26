package com.bekmnsrw.anistore.dto.product;

import com.bekmnsrw.anistore.model.enums.ProductCategory;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Product DTO")
public class ProductDto {
    @Schema(description = "Product's id", example = "1")
    private Long id;
    @Schema(description = "Product's name", example = "Naruto Uzumaki")
    private String title;
    @Schema(description = "Product's description", example = "Add it to your collection!")
    private String description;
    @Schema(description = "Product image's URL", example = "https://firebasestorage.googleapis.com/v0/b/anistore-web.appspot.com/o/1.png?alt=media&token=0afcc42c-d502-4e10-815d-66a309808c8c")
    private String imageUrl;
    @Schema(description = "Product's price", example = "1999.99")
    private Double price;
    @Schema(description = "Product's category", example = "FIGURES")
    private ProductCategory productCategory;
}

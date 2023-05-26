package com.bekmnsrw.anistore.dto.product;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Page with list of products and total amount of such pages")
public class ProductPage {
    @Schema(description = "List of products")
    private List<ProductDto> products;
    @Schema(description = "Total amount of available pages")
    private Integer totalPages;
}

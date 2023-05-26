package com.bekmnsrw.anistore.model.enums;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Product's category")
public enum ProductCategory {
    @Schema(description = "MANGA category") MANGA,
    @Schema(description = "SNACKS category") SNACKS,
    @Schema(description = "FIGURES category") FIGURES
}

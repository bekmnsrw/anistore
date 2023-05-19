package com.bekmnsrw.anistore.dto;

import com.bekmnsrw.anistore.model.enums.CartStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartDto {
    private Long id;
    private CartStatus cartStatus;
}

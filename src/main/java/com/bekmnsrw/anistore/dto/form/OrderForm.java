package com.bekmnsrw.anistore.dto.form;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderForm {
    private String address;
    private Double orderPrice;
    private String promoCode;
}

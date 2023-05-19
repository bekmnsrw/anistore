package com.bekmnsrw.anistore.service;

import com.bekmnsrw.anistore.model.Discount;

public interface DiscountService {

    Discount getPromoCode(String promoCode);
    Double applyPromoCode(String promoCode, Double orderPrice);
}

package com.bekmnsrw.anistore.service.impl;

import com.bekmnsrw.anistore.model.Discount;
import com.bekmnsrw.anistore.repository.DiscountRepository;
import com.bekmnsrw.anistore.service.DiscountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DiscountServiceImpl implements DiscountService {

    private final DiscountRepository discountRepository;

    @Override
    public Discount getPromoCode(String promoCode) {
        return discountRepository
                .findByPromoCode(promoCode)
                .orElse(null);
    }

    @Override
    public Double applyPromoCode(String promoCode, Double orderPrice) {
        Discount discount = this.getPromoCode(promoCode);
        if (discount != null) {
            return orderPrice - orderPrice * discount.getPercentage() / 100;
        } else {
            return orderPrice;
        }
    }
}

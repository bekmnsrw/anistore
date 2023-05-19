package com.bekmnsrw.anistore.controller.rest;

import com.bekmnsrw.anistore.model.Discount;
import com.bekmnsrw.anistore.service.DiscountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/discount")
public class DiscountRestController {

    private final DiscountService discountService;

    @GetMapping
    public ResponseEntity<Long> getPromoCode(
            @RequestParam("promoCode") String promoCode
    ) {
        Discount discount = discountService.getPromoCode(promoCode);
        if (discount != null) {
            return ResponseEntity.ok(discount.getPercentage());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}

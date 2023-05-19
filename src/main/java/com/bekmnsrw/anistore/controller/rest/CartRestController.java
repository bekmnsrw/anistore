package com.bekmnsrw.anistore.controller.rest;

import com.bekmnsrw.anistore.service.CartItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartRestController {

    private final CartItemService cartItemService;

    @PutMapping
    public ResponseEntity<?> updateProductAmountInCart(
            @RequestParam(value = "btnPlus") String btnPlus,
            @RequestParam(value = "btnMinus") String btnMinus
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (!btnPlus.isEmpty()) {
            cartItemService.increaseProductAmountInController(
                    authentication.getName(),
                    Long.valueOf(btnPlus)
            );
        }

        if (!btnMinus.isEmpty()) {
            cartItemService.decreaseProductAmountInController(
                    authentication.getName(),
                    Long.valueOf(btnMinus)
            );
        }

        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .build();
    }

    @DeleteMapping
    public ResponseEntity<?> deleteProductFromCart(
            @RequestParam("productId") Long productId
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        cartItemService.deleteProductFromCart(
                authentication.getName(),
                productId
        );

        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .build();
    }
}

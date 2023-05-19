package com.bekmnsrw.anistore.controller.rest;

import com.bekmnsrw.anistore.service.CartItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/catalog")
@SessionAttributes("count")
@RequiredArgsConstructor
public class CatalogRestController {

    private final CartItemService cartItemService;

    private static final String ROLE_ANONYMOUS = "ROLE_ANONYMOUS";

    @PostMapping
    public ResponseEntity<?> addProductToCart(
            @RequestParam("productId") Long productId
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String role = authentication.getAuthorities().toArray()[0].toString();

        if (!role.equals(ROLE_ANONYMOUS)) {
            cartItemService.addProductToCartInController(authentication.getName(), productId);
        }

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }
}

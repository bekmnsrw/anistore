package com.bekmnsrw.anistore.controller.rest;

import com.bekmnsrw.anistore.service.CartItemService;
import com.bekmnsrw.anistore.service.CatalogService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest/catalog")
@SessionAttributes("products")
@RequiredArgsConstructor
public class CatalogRestController {

    private final CartItemService cartItemService;
    private final CatalogService catalogService;

    private static final String ROLE_ANONYMOUS = "ROLE_ANONYMOUS";

    @PostMapping
    public ResponseEntity<String> addProductToCart(
            @RequestParam("productId") Long productId
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String role = authentication.getAuthorities().toArray()[0].toString();
        String result = "error";

        if (!role.equals(ROLE_ANONYMOUS)) {
            cartItemService.addProductToCartInController(authentication.getName(), productId);
            result = "success";
        }

        return ResponseEntity.ok(result);
    }

    @GetMapping
    public ResponseEntity<?> filter(
            @RequestParam(value = "filter", required = false) String filterParam,
            HttpServletRequest httpServletRequest
    ) {
        httpServletRequest.getSession().setAttribute("products", catalogService.getAllByFilter(filterParam));
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(catalogService.getAllByFilter(filterParam));
    }
}

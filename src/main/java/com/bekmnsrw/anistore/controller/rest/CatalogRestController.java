package com.bekmnsrw.anistore.controller.rest;

import com.bekmnsrw.anistore.dto.product.ProductDto;
import com.bekmnsrw.anistore.service.CartItemService;
import com.bekmnsrw.anistore.service.CatalogService;
import com.bekmnsrw.anistore.service.ProductService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/catalog")
@RequiredArgsConstructor
public class CatalogRestController {

    private final CartItemService cartItemService;
    private final CatalogService catalogService;
    private final ProductService productService;

    private static final String ROLE_ANONYMOUS = "ROLE_ANONYMOUS";

    @PostMapping
    public ResponseEntity<String> addProductToCart(
            @RequestParam("productId") Long productId,
            HttpSession httpSession
    ) {
        String role = httpSession.getAttribute("role").toString();

        if (!role.equals(ROLE_ANONYMOUS)) {
            cartItemService.addProductToCartInController(httpSession.getAttribute("email").toString(), productId);
            return ResponseEntity.ok("success");
        }

        return ResponseEntity.ok("error");
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> filter(
            @RequestParam(value = "filter", required = false) String filterParam,
            HttpSession httpSession
    ) {
        List<ProductDto> products = catalogService.getAllByFilter(filterParam);
        httpSession.setAttribute("products", products);
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(products);
    }

    @GetMapping("/unordered")
    public ResponseEntity<?> unorderedProducts(
            @RequestParam(value = "cartId", required = false) Long cartId,
            HttpSession httpSession
    ) {
        List<ProductDto> products = productService.findUnorderedProducts(cartId);
        httpSession.setAttribute("products", products);
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(products);
    }
}

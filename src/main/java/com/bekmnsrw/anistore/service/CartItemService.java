package com.bekmnsrw.anistore.service;

import com.bekmnsrw.anistore.dto.CartItemDto;
import com.bekmnsrw.anistore.dto.ProductDto;

import java.util.List;

public interface CartItemService {

    Boolean isInCart(Long cartId, Long productId);
    void addProductToCart(Long cartId, Long productId);
    List<ProductDto> getAllProductsInCart(Long cartId);
    void increaseProductAmount(Long cartId, Long productId);
    void decreaseProductAmount(Long cartId, Long productId);

    List<CartItemDto> getUserCartInController(String email);
    void addProductToCartInController(String email, Long productId);
    void increaseProductAmountInController(String email, Long productId);
    void decreaseProductAmountInController(String email, Long productId);
}

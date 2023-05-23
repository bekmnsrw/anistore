package com.bekmnsrw.anistore.service;

import com.bekmnsrw.anistore.dto.CartItemDto;
import com.bekmnsrw.anistore.dto.product.ProductDto;

import java.util.List;

public interface CartItemService {

    Boolean isInCart(Long cartId, Long productId);
    void addProductToCart(Long cartId, Long productId);
    List<ProductDto> getAllProductsInCart(Long cartId);
    List<CartItemDto> getUserCartInController(String email);
    void addProductToCartInController(String email, Long productId);
    void increaseProductAmountInController(String email, Long productId);
    void decreaseProductAmountInController(String email, Long productId);
    void deleteProductFromCart(String email, Long productId);
    List<ProductDto> findAllProductsInCart(Long cartId);
    CartItemDto findByCartIdAndProductId(Long cartId, Long productId);
}

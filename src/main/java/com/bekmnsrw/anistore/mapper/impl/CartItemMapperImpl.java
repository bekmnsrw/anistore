package com.bekmnsrw.anistore.mapper.impl;

import com.bekmnsrw.anistore.dto.CartItemDto;
import com.bekmnsrw.anistore.mapper.CartItemMapper;
import com.bekmnsrw.anistore.model.CartItem;
import org.springframework.stereotype.Service;

@Service
public class CartItemMapperImpl implements CartItemMapper {

    @Override
    public CartItemDto from(CartItem cartItem) {
        return CartItemDto.builder()
                .productId(cartItem.getProduct().getId())
                .productTitle(cartItem.getProduct().getTitle())
                .productImageUrl(cartItem.getProduct().getImageUrl())
                .productPrice(cartItem.getProduct().getPrice())
                .productCategory(cartItem.getProduct().getCategory())
                .productAmount(cartItem.getProductAmount())
                .build();
    }
}

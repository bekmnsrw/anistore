package com.bekmnsrw.anistore.mapper.impl;

import com.bekmnsrw.anistore.dto.CartDto;
import com.bekmnsrw.anistore.mapper.CartMapper;
import com.bekmnsrw.anistore.model.Cart;
import com.bekmnsrw.anistore.model.User;
import org.springframework.stereotype.Component;

@Component
public class CartMapperImpl implements CartMapper {

    @Override
    public CartDto from(Cart cart) {
        return CartDto.builder()
                .id(cart.getId())
                .cartStatus(cart.getCartStatus())
                .build();
    }

    @Override
    public Cart from(CartDto cartDto, User user) {
        return Cart.builder()
                .id(cartDto.getId())
                .cartStatus(cartDto.getCartStatus())
                .user(user)
                .build();
    }
}

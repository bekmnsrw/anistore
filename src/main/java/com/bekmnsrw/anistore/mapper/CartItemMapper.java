package com.bekmnsrw.anistore.mapper;

import com.bekmnsrw.anistore.dto.CartItemDto;
import com.bekmnsrw.anistore.model.CartItem;

public interface CartItemMapper {

    CartItemDto from(CartItem cartItem);
}

package com.bekmnsrw.anistore.mapper;

import com.bekmnsrw.anistore.dto.CartDto;
import com.bekmnsrw.anistore.model.Cart;
import com.bekmnsrw.anistore.model.User;

public interface CartMapper {

    CartDto from(Cart cart);
    Cart from(CartDto cartDto, User user);
}

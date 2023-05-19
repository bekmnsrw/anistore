package com.bekmnsrw.anistore.service;

import com.bekmnsrw.anistore.dto.CartDto;
import com.bekmnsrw.anistore.model.Cart;

import java.util.Optional;

public interface CartService {

    CartDto createCart(String email);
    CartDto findCurrentCart(String email);
    Optional<Cart> findById(Long id);
    void markCurrentCartAsInactive(String email);
}

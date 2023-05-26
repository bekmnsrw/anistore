package com.bekmnsrw.anistore.service.impl;

import com.bekmnsrw.anistore.dto.CartDto;
import com.bekmnsrw.anistore.mapper.CartMapper;
import com.bekmnsrw.anistore.model.Cart;
import com.bekmnsrw.anistore.model.User;
import com.bekmnsrw.anistore.model.enums.CartStatus;
import com.bekmnsrw.anistore.repository.CartRepository;
import com.bekmnsrw.anistore.repository.UserRepository;
import com.bekmnsrw.anistore.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final CartMapper cartMapper;

    @Override
    public CartDto createCart(String email) {
        System.out.println("Create cart");
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isPresent()) {
            Cart cart = Cart.builder()
                    .user(optionalUser.get())
                    .cartStatus(CartStatus.ACTIVE)
                    .build();

            return cartMapper.from(cartRepository.save(cart));
        } else {
            return null;
        }
    }

    @Override
    public CartDto findCurrentCart(String email) {
        Optional<Cart> optionalCart = cartRepository.findByUserEmail(email);
        if (optionalCart.isPresent()) {
            System.out.println("Find cart, cart is present");
            return cartMapper.from(optionalCart.get());
        } else {
            System.out.println("Find current cart, cart is null");
            return this.createCart(email);
        }
    }

    @Override
    public Optional<Cart> findById(Long id) {
        return cartRepository.findById(id);
    }

    @Override
    public void markCurrentCartAsInactive(String email) {
        User user = userRepository.findByEmail(email).get();
        CartDto cartDto = this.findCurrentCart(email);
        cartDto.setCartStatus(CartStatus.INACTIVE);
        cartRepository.save(cartMapper.from(cartDto, user));
    }

    @Override
    public List<CartDto> findAllInactiveCarts(String email) {
        return cartRepository.findAllCarts(email)
                .stream()
                .map(cartMapper::from)
                .collect(Collectors.toList());
    }
}

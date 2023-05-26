package com.bekmnsrw.anistore.service.impl;

import com.bekmnsrw.anistore.dto.CartDto;
import com.bekmnsrw.anistore.dto.CartItemDto;
import com.bekmnsrw.anistore.dto.product.ProductDto;
import com.bekmnsrw.anistore.mapper.CartItemMapper;
import com.bekmnsrw.anistore.mapper.ProductMapper;
import com.bekmnsrw.anistore.model.Cart;
import com.bekmnsrw.anistore.model.CartItem;
import com.bekmnsrw.anistore.model.Product;
import com.bekmnsrw.anistore.repository.CartItemRepository;
import com.bekmnsrw.anistore.service.CartItemService;
import com.bekmnsrw.anistore.service.CartService;
import com.bekmnsrw.anistore.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartItemServiceImpl implements CartItemService {

    private final CartItemRepository cartItemRepository;
    private final ProductService productService;
    private final CartService cartService;
    private final ProductMapper productMapper;
    private final CartItemMapper cartItemMapper;

    @Override
    public Boolean isInCart(Long cartId, Long productId) {
        return cartItemRepository.findByCartIdAndProductId(cartId, productId) != null;
    }

    @Override
    public void addProductToCart(Long cartId, Long productId) {
        Optional<Cart> cart = cartService.findById(cartId);
        Optional<Product> product = productService.findById(productId);

        if (cart.isPresent() && product.isPresent() && !isInCart(cartId, productId)) {
            CartItem cartItem = CartItem.builder()
                    .cart(cart.get())
                    .product(product.get())
                    .productAmount(1L)
                    .build();

            cartItemRepository.save(cartItem);
        }
    }

    @Override
    public List<ProductDto> getAllProductsInCart(Long cartId) {
        return productMapper.from(cartItemRepository.getAllProductsIdsInCart(cartId));
    }

    @Override
    public List<CartItemDto> getUserCartInController(String email) {
        List<CartItemDto> result = new ArrayList<>();

        System.out.println("In controller");

        CartDto cartDto = cartService.findCurrentCart(email);

        if (cartDto != null) {
            List<ProductDto> productsInCart = this.getAllProductsInCart(cartDto.getId());
            List<Long> productsAmount = cartItemRepository.getAllProductsAmountInCart(cartDto.getId());

            for (int i = 0; i < productsInCart.size(); i++) {
                result.add(productMapper.from(productsInCart.get(i), productsAmount.get(i)));
            }
        }

        return result;
    }

    @Override
    public void addProductToCartInController(String email, Long productId) {
        CartDto cartDto = cartService.findCurrentCart(email);
        this.addProductToCart(cartDto.getId(), productId);
        System.out.println("Add product to cart");
//        if (cartDto == null) {
//            CartDto newCart = cartService.createCart(email);
//            this.addProductToCart(newCart.getId(), productId);
//        } else {
//            this.addProductToCart(cartDto.getId(), productId);
//        }
    }

    @Override
    public void increaseProductAmountInController(String email, Long productId) {
        CartDto cartDto = cartService.findCurrentCart(email);
        cartItemRepository.increaseProductAmount(cartDto.getId(), productId);
    }

    @Override
    public void decreaseProductAmountInController(String email, Long productId) {
        CartDto cartDto = cartService.findCurrentCart(email);
        cartItemRepository.decreaseProductAmount(cartDto.getId(), productId);
    }

    @Override
    public void deleteProductFromCart(String email, Long productId) {
        CartDto cartDto = cartService.findCurrentCart(email);
        cartItemRepository.deleteByCartIdAndProductId(cartDto.getId(), productId);
    }

    @Override
    public List<ProductDto> findAllProductsInCart(Long cartId) {
        return productMapper.from(cartItemRepository.findAllProductsInCart(cartId));
    }

    @Override
    public CartItemDto findByCartIdAndProductId(Long cartId, Long productId) {
        return cartItemMapper.from(cartItemRepository.findByCartIdAndProductId(cartId, productId));
    }
}

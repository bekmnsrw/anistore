package com.bekmnsrw.anistore.service.impl;

import com.bekmnsrw.anistore.dto.CartItemDto;
import com.bekmnsrw.anistore.dto.form.OrderForm;
import com.bekmnsrw.anistore.mapper.CartMapper;
import com.bekmnsrw.anistore.model.Cart;
import com.bekmnsrw.anistore.model.Discount;
import com.bekmnsrw.anistore.model.Order;
import com.bekmnsrw.anistore.model.User;
import com.bekmnsrw.anistore.model.enums.OrderStatus;
import com.bekmnsrw.anistore.repository.OrderRepository;
import com.bekmnsrw.anistore.repository.UserRepository;
import com.bekmnsrw.anistore.service.CartItemService;
import com.bekmnsrw.anistore.service.CartService;
import com.bekmnsrw.anistore.service.DiscountService;
import com.bekmnsrw.anistore.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final CartItemService cartItemService;
    private final CartService cartService;
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final CartMapper cartMapper;
    private final DiscountService discountService;

    @Override
    public Double getTotalOrderPrice(String email) {
        List<CartItemDto> items = cartItemService.getUserCartInController(email);
        AtomicReference<Double> total = new AtomicReference<>(0.0);
        items.forEach(item -> total.updateAndGet(v -> v + item.getProductPrice() * item.getProductAmount()));
        return total.get();
    }

    @Override
    public void makeOrder(OrderForm orderForm, String email) {
        User user = userRepository.findByEmail(email).get();
        Cart cart = cartMapper.from(cartService.findCurrentCart(email), user);
        Discount discount = discountService.getPromoCode(orderForm.getPromoCode());

        Order order = Order.builder()
                .createdAt("19.05.2023")
                .deliveryAddress(orderForm.getAddress())
                .orderStatus(OrderStatus.CREATED)
                .totalOrderPrice(discountService.applyPromoCode(orderForm.getPromoCode(), orderForm.getOrderPrice()))
                .cart(cart)
                .discount(discount)
                .build();

        orderRepository.save(order);
    }
}

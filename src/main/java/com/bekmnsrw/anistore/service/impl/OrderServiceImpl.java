package com.bekmnsrw.anistore.service.impl;

import com.bekmnsrw.anistore.dto.CartDto;
import com.bekmnsrw.anistore.dto.CartItemDto;
import com.bekmnsrw.anistore.dto.OrderDto;
import com.bekmnsrw.anistore.dto.form.OrderForm;
import com.bekmnsrw.anistore.dto.form.OrderHistoryDto;
import com.bekmnsrw.anistore.mapper.CartMapper;
import com.bekmnsrw.anistore.mapper.OrderMapper;
import com.bekmnsrw.anistore.model.*;
import com.bekmnsrw.anistore.model.enums.OrderStatus;
import com.bekmnsrw.anistore.repository.CartItemRepository;
import com.bekmnsrw.anistore.repository.OrderRepository;
import com.bekmnsrw.anistore.repository.UserRepository;
import com.bekmnsrw.anistore.service.CartItemService;
import com.bekmnsrw.anistore.service.CartService;
import com.bekmnsrw.anistore.service.DiscountService;
import com.bekmnsrw.anistore.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    private final CartItemRepository cartItemRepository;
    private final OrderMapper orderMapper;

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
        cartService.markCurrentCartAsInactive(email);
    }

    @Override
    public List<OrderHistoryDto> getOrderHistory(String email) {
        List<CartDto> inactiveCarts = cartService.findAllInactiveCarts(email);
        List<OrderDto> createdOrders = new ArrayList<>();
        List<OrderHistoryDto> orderHistory = new ArrayList<>();
        Map<Long, List<String>> productsInOrder = new HashMap<>();

        for (CartDto cartDto : inactiveCarts) {
            OrderDto orderDto = orderMapper.from(orderRepository.findByCartId(cartDto.getId()));
            List<Product> products = cartItemRepository.findAllProductsInCart(cartDto.getId());
            createdOrders.add(orderDto);

            List<String> p = new ArrayList<>();

            for (Product product : products) {
                CartItem cartItem = cartItemRepository.findByCartIdAndProductId(cartDto.getId(), product.getId());
                p.add(product.getTitle() + ": " + cartItem.getProductAmount());
            }

            productsInOrder.put(orderDto.getId(), p);
        }

        for (OrderDto orderDto : createdOrders) {
            OrderHistoryDto orderHistoryDto = OrderHistoryDto.builder()
                    .address(orderDto.getDeliveryAddress())
                    .price(orderDto.getTotalOrderPrice())
                    .status(orderDto.getOrderStatus())
                    .products(productsInOrder.get(orderDto.getId()))
                    .createdAt(orderDto.getCreatedAt())
                    .build();

            orderHistory.add(orderHistoryDto);
        }

        return orderHistory;
    }
}

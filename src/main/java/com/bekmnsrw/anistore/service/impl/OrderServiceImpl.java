package com.bekmnsrw.anistore.service.impl;

import com.bekmnsrw.anistore.dto.CartDto;
import com.bekmnsrw.anistore.dto.CartItemDto;
import com.bekmnsrw.anistore.dto.order.OrderDto;
import com.bekmnsrw.anistore.dto.order.OrderPage;
import com.bekmnsrw.anistore.dto.product.ProductDto;
import com.bekmnsrw.anistore.dto.form.OrderForm;
import com.bekmnsrw.anistore.dto.form.OrderHistoryDto;
import com.bekmnsrw.anistore.mapper.CartMapper;
import com.bekmnsrw.anistore.mapper.OrderMapper;
import com.bekmnsrw.anistore.model.Cart;
import com.bekmnsrw.anistore.model.Discount;
import com.bekmnsrw.anistore.model.Order;
import com.bekmnsrw.anistore.model.User;
import com.bekmnsrw.anistore.model.enums.OrderStatus;
import com.bekmnsrw.anistore.repository.OrderRepository;
import com.bekmnsrw.anistore.repository.UserRepository;
import com.bekmnsrw.anistore.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final CartItemService cartItemService;
    private final CartService cartService;
    private final UserRepository userRepository;
    private final DiscountService discountService;
    private final CartMapper cartMapper;
    private final OrderMapper orderMapper;

    @Value("${application.default-page-size}")
    private Integer defaultPageSize;

    private static final String DATE_PATTERN = "yyyy-MM-dd HH:mm";

    @Override
    public Double getTotalOrderPrice(String email) {
        List<CartItemDto> items = cartItemService.getUserCartInController(email);
        AtomicReference<Double> total = new AtomicReference<>(0.0);
        items.forEach(item -> total.updateAndGet(v -> v + item.getProductPrice() * item.getProductAmount()));
        return total.get();
    }

    @Override
    public void makeOrder(OrderForm orderForm, String email) {
        User user = userRepository.findByEmail(email).get();;
        Cart cart = cartMapper.from(cartService.findCurrentCart(email), user);
        Discount discount = discountService.getPromoCode(orderForm.getPromoCode());

        Order order = Order.builder()
                .createdAt(currentDate())
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
            List<ProductDto> products = cartItemService.findAllProductsInCart(cartDto.getId());
            createdOrders.add(orderDto);

            List<String> p = new ArrayList<>();

            for (ProductDto product : products) {
                CartItemDto cartItemDto = cartItemService.findByCartIdAndProductId(cartDto.getId(), product.getId());
                p.add(product.getTitle() + ": " + cartItemDto.getProductAmount());
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

    @Override
    public OrderPage getAll(Integer page) {
        PageRequest pageRequest = PageRequest.of(page, defaultPageSize);
        Page<Order> orderPage = orderRepository.findAll(pageRequest);

        return OrderPage.builder()
                .orders(orderMapper.from(orderPage.getContent()))
                .totalPages(orderPage.getTotalPages())
                .build();
    }

    @Override
    public OrderDto updateOrderStatus(Long orderId, String status) {
        Order order = orderRepository.findById(orderId).get();
        order.setOrderStatus(OrderStatus.valueOf(status));
        return orderMapper.from(orderRepository.save(order));
    }

    private String currentDate() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DATE_PATTERN);
        LocalDateTime now = LocalDateTime.now();
        return dateTimeFormatter.format(now);
    }
}

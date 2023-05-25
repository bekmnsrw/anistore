package com.bekmnsrw.anistore.controller.rest;

import com.bekmnsrw.anistore.dto.order.OrderDto;
import com.bekmnsrw.anistore.dto.order.OrderPage;
import com.bekmnsrw.anistore.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rest/orders")
public class OrderRestController {

    private final OrderService orderService;

    @GetMapping
    public ResponseEntity<OrderPage> getAll(
            @RequestParam("page") Integer page
    ) {
        return ResponseEntity
                .ok(orderService.getAll(page));
    }

    @PutMapping("/{order-id}")
    public ResponseEntity<OrderDto> updateOrderStatus(
            @PathVariable("order-id") Long orderId,
            @RequestParam("status") String status
    ) {
        return ResponseEntity.ok(
                orderService.updateOrderStatus(orderId, status)
        );
    }
}

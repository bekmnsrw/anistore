package com.bekmnsrw.anistore.model;

import com.bekmnsrw.anistore.model.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "created_at")
    private String createdAt;

    @Column(name = "delivery_address")
    private String deliveryAddress;

    @Enumerated(EnumType.STRING)
    @Column(name = "order_status")
    private OrderStatus orderStatus;

    @ManyToOne
    @JoinColumn(name = "discount_id")
    private Discount discount;

    @Column(name = "total_order_price")
    private Double totalOrderPrice;

    @OneToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;
}

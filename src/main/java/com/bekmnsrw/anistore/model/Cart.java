package com.bekmnsrw.anistore.model;

import com.bekmnsrw.anistore.model.enums.CartStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "carts")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(name = "cart_status")
    private CartStatus cartStatus;

    @Column(name = "total_products_amount")
    private Long totalProductsAmount;

    @Column(name = "total_cart_price")
    private Double totalCartPrice;
}

package com.bekmnsrw.anistore.repository;

import com.bekmnsrw.anistore.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

    Order findByCartId(Long cartId);
}

package com.bekmnsrw.anistore.repository;

import com.bekmnsrw.anistore.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
}

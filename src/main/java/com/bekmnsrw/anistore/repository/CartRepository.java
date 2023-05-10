package com.bekmnsrw.anistore.repository;

import com.bekmnsrw.anistore.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
}

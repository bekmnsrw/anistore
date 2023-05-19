package com.bekmnsrw.anistore.repository;

import com.bekmnsrw.anistore.dto.CartDto;
import com.bekmnsrw.anistore.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {

    @Query("SELECT c FROM carts c WHERE c.user.email = :email AND c.cartStatus = 'ACTIVE'")
    Optional<Cart> findByUserEmail(String email);

    @Query("SELECT c FROM carts c WHERE c.user.email = :email AND c.cartStatus = 'INACTIVE'")
    List<Cart> findAllCarts(String email);
}

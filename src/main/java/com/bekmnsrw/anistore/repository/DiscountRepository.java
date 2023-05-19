package com.bekmnsrw.anistore.repository;

import com.bekmnsrw.anistore.model.Discount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DiscountRepository extends JpaRepository<Discount, Long> {

    Optional<Discount> findByPromoCode(String promoCode);
}

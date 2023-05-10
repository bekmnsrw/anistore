package com.bekmnsrw.anistore.repository;

import com.bekmnsrw.anistore.model.Discount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiscountRepository extends JpaRepository<Discount, Long> {
}

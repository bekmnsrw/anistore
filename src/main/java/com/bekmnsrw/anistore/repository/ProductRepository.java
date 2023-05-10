package com.bekmnsrw.anistore.repository;

import com.bekmnsrw.anistore.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}

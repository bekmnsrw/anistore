package com.bekmnsrw.anistore.repository;

import com.bekmnsrw.anistore.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllByOrderByPrice();

    List<Product> findAllByOrderByPriceDesc();

    @Query(value = "SELECT p FROM products p WHERE p.category = 'FIGURES'")
    List<Product> filterFigures();

    @Query(value = "SELECT p FROM products p WHERE p.category = 'SNACKS'")
    List<Product> filterSnacks();

    @Query(value = "SELECT p FROM products p WHERE p.category = 'MANGA'")
    List<Product> filterManga();
}

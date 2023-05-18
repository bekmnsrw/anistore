package com.bekmnsrw.anistore.service;

import com.bekmnsrw.anistore.model.Product;

import java.util.Optional;

public interface ProductService {

    Optional<Product> findById(Long id);
}

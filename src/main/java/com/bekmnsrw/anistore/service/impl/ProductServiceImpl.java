package com.bekmnsrw.anistore.service.impl;

import com.bekmnsrw.anistore.model.Product;
import com.bekmnsrw.anistore.repository.ProductRepository;
import com.bekmnsrw.anistore.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public Optional<Product> findById(Long id) { return productRepository.findById(id); }
}

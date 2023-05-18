package com.bekmnsrw.anistore.service.impl;

import com.bekmnsrw.anistore.dto.ProductDto;
import com.bekmnsrw.anistore.mapper.ProductMapper;
import com.bekmnsrw.anistore.model.Product;
import com.bekmnsrw.anistore.repository.ProductRepository;
import com.bekmnsrw.anistore.service.CatalogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CatalogServiceImpl implements CatalogService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public List<ProductDto> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(productMapper::from)
                .collect(Collectors.toList());
    }

    @Override
    public ProductDto getById(Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);

        if (optionalProduct.isEmpty()) {
            throw new RuntimeException("Product with id <" + id + "> not found");
        }

        return productMapper.from(optionalProduct.get());
    }
}

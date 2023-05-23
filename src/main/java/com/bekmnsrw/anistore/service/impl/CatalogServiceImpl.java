package com.bekmnsrw.anistore.service.impl;

import com.bekmnsrw.anistore.dto.product.ProductDto;
import com.bekmnsrw.anistore.mapper.ProductMapper;
import com.bekmnsrw.anistore.model.Product;
import com.bekmnsrw.anistore.repository.ProductRepository;
import com.bekmnsrw.anistore.service.CatalogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CatalogServiceImpl implements CatalogService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    private static final String MANGA = "MANGA";
    private static final String SNACKS = "SNACKS";
    private static final String FIGURES = "FIGURES";
    private static final String NONE = "NONE";
    private static final String CHEAPEST_FIRST = "CHEAPEST_FIRST";
    private static final String EXPENSIVE_FIRST = "EXPENSIVE_FIRST";

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

    @Override
    public List<ProductDto> getAllByFilter(String filter) {
        List<ProductDto> result = new ArrayList<>();

        switch (filter) {
            case MANGA -> result = productMapper.from(productRepository.filterManga());
            case FIGURES -> result = productMapper.from(productRepository.filterFigures());
            case SNACKS -> result = productMapper.from(productRepository.filterSnacks());
            case NONE -> result = productMapper.from(productRepository.findAll());
            case CHEAPEST_FIRST -> result = productMapper.from(productRepository.findAllByOrderByPrice());
            case EXPENSIVE_FIRST -> result = productMapper.from(productRepository.findAllByOrderByPriceDesc());
        }

        return result;
    }
}

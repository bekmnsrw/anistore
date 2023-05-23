package com.bekmnsrw.anistore.service.impl;

import com.bekmnsrw.anistore.dto.product.NewOrUpdatedProductDto;
import com.bekmnsrw.anistore.dto.product.ProductDto;
import com.bekmnsrw.anistore.dto.product.ProductPage;
import com.bekmnsrw.anistore.exception.NotFoundException;
import com.bekmnsrw.anistore.mapper.ProductMapper;
import com.bekmnsrw.anistore.model.Product;
import com.bekmnsrw.anistore.repository.ProductRepository;
import com.bekmnsrw.anistore.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Value("${application.default-page-size}")
    private Integer defaultPageSize;

    @Override
    public Optional<Product> findById(Long id) { return productRepository.findById(id); }

    // REST
    @Override
    public ProductDto saveProduct(NewOrUpdatedProductDto newProductDto) {
        Product product = Product.builder()
                .title(newProductDto.getTitle())
                .description(newProductDto.getDescription())
                .imageUrl(newProductDto.getImageUrl())
                .price(newProductDto.getPrice())
                .category(newProductDto.getCategory())
                .build();

        return productMapper.from(productRepository.save(product));
    }

    @Override
    public ProductDto findProductDtoById(Long id) { return productMapper.from(getOrThrow(id)); }

    @Override
    public Product findProductById(Long id) { return getOrThrow(id); }

    @Override
    public ProductDto updateProduct(Long id, NewOrUpdatedProductDto updatedProductDto) {
        Product product = getOrThrow(id);

        if (updatedProductDto.getTitle() != null) {
            product.setTitle(updatedProductDto.getTitle());
        }

        if (updatedProductDto.getDescription() != null) {
            product.setDescription(updatedProductDto.getDescription());
        }

        if (updatedProductDto.getImageUrl() != null) {
            product.setImageUrl(updatedProductDto.getImageUrl());
        }

        if (updatedProductDto.getPrice() != null) {
            product.setPrice(updatedProductDto.getPrice());
        }

        if (updatedProductDto.getCategory() != null) {
            product.setCategory(updatedProductDto.getCategory());
        }

        return productMapper.from(productRepository.save(product));
    }

    @Override
    public void deleteProduct(Long id) { productRepository.delete(getOrThrow(id)); }

    @Override
    public ProductPage getAll(Integer page) {
        PageRequest pageRequest = PageRequest.of(page, defaultPageSize);
        Page<Product> productPage = productRepository.findAll(pageRequest);

        return ProductPage.builder()
                .products(productMapper.from(productPage.getContent()))
                .totalPages(productPage.getTotalPages())
                .build();
    }

    private Product getOrThrow(Long id) {
        return productRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Can't find product with id: " + id));
    }
}

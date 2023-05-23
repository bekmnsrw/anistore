package com.bekmnsrw.anistore.mapper.impl;

import com.bekmnsrw.anistore.dto.CartItemDto;
import com.bekmnsrw.anistore.dto.product.ProductDto;
import com.bekmnsrw.anistore.mapper.ProductMapper;
import com.bekmnsrw.anistore.model.Product;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductMapperImpl implements ProductMapper {

    @Override
    public ProductDto from(Product product) {
        return ProductDto.builder()
                .id(product.getId())
                .title(product.getTitle())
                .description(product.getDescription())
                .imageUrl(product.getImageUrl())
                .price(product.getPrice())
                .productCategory(product.getCategory())
                .build();
    }

    @Override
    public List<ProductDto> from(List<Product> products) {
        return products.stream()
                .map(this::from)
                .collect(Collectors.toList());
    }

    @Override
    public CartItemDto from(ProductDto productDto, Long productAmount) {
        return CartItemDto.builder()
                .productId(productDto.getId())
                .productTitle(productDto.getTitle())
                .productImageUrl(productDto.getImageUrl())
                .productPrice(productDto.getPrice())
                .productCategory(productDto.getProductCategory())
                .productAmount(productAmount)
                .build();
    }
}

package com.bekmnsrw.anistore.mapper;

import com.bekmnsrw.anistore.dto.CartItemDto;
import com.bekmnsrw.anistore.dto.product.ProductDto;
import com.bekmnsrw.anistore.model.Product;

import java.util.List;

public interface ProductMapper {

    ProductDto from(Product product);
    List<ProductDto> from(List<Product> products);
    CartItemDto from(ProductDto productDto, Long productAmount);
}

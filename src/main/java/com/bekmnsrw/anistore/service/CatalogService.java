package com.bekmnsrw.anistore.service;

import com.bekmnsrw.anistore.dto.product.ProductDto;

import java.util.List;

public interface CatalogService {

    List<ProductDto> getAllProducts();
    ProductDto getById(Long id);
    List<ProductDto> getAllByFilter(String filter);
}

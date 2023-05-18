package com.bekmnsrw.anistore.service;

import com.bekmnsrw.anistore.dto.ProductDto;

import java.util.List;

public interface CatalogService {

    List<ProductDto> getAllProducts();
    ProductDto getById(Long id);
}

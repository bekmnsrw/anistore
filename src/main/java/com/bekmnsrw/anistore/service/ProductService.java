package com.bekmnsrw.anistore.service;

import com.bekmnsrw.anistore.dto.product.NewOrUpdatedProductDto;
import com.bekmnsrw.anistore.dto.product.ProductDto;
import com.bekmnsrw.anistore.dto.product.ProductPage;
import com.bekmnsrw.anistore.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    Optional<Product> findById(Long id);

    // REST
    ProductDto saveProduct(NewOrUpdatedProductDto newProductDto);
    ProductDto findProductDtoById(Long id);
    Product findProductById(Long id);
    ProductDto updateProduct(Long id, NewOrUpdatedProductDto updatedProductDto);
    void deleteProduct(Long id);
    ProductPage getAll(Integer page);
    List<ProductDto> findUnorderedProducts(Long cartId);
}

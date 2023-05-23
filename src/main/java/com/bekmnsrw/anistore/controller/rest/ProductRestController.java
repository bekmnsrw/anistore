package com.bekmnsrw.anistore.controller.rest;

import com.bekmnsrw.anistore.dto.product.NewOrUpdatedProductDto;
import com.bekmnsrw.anistore.dto.product.ProductDto;
import com.bekmnsrw.anistore.dto.product.ProductPage;
import com.bekmnsrw.anistore.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rest/products")
public class ProductRestController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ProductDto> save(
            @RequestBody NewOrUpdatedProductDto newProductDto
    ) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(productService.saveProduct(newProductDto));
    }

    @GetMapping
    public ResponseEntity<ProductPage> getAll(
            @RequestParam("page") Integer page
    ) {
        return ResponseEntity
                .ok(productService.getAll(page));
    }

    @GetMapping("/{product-id}")
    public ResponseEntity<ProductDto> get(
            @PathVariable("product-id") Long id
    ) {
        return ResponseEntity
                .ok(productService.findProductDtoById(id));
    }

    @PutMapping("/{product-id}")
    public ResponseEntity<ProductDto> update(
            @PathVariable("product-id") Long id,
            @RequestBody NewOrUpdatedProductDto updatedProductDto
    ) {
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(productService.updateProduct(id, updatedProductDto));
    }

    @DeleteMapping("/{product-id}")
    public ResponseEntity<?> delete(
            @PathVariable("product-id") Long id
    ) {
        productService.deleteProduct(id);
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .build();
    }
}

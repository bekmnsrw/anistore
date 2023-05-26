package com.bekmnsrw.anistore.controller.rest;

import com.bekmnsrw.anistore.dto.product.NewOrUpdatedProductDto;
import com.bekmnsrw.anistore.dto.product.ProductDto;
import com.bekmnsrw.anistore.dto.product.ProductPage;
import com.bekmnsrw.anistore.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rest/products")
public class ProductRestController {

    private final ProductService productService;

    @Operation(summary = "Saving new product")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Product successfully saved",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ProductDto.class)
                            )
                    }
            )
    })
    @PostMapping
    public ResponseEntity<ProductDto> save(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "New product DTO") @RequestBody NewOrUpdatedProductDto newProductDto
    ) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(productService.saveProduct(newProductDto));
    }

    @Operation(summary = "Receiving products with pagination")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Page with products successfully received",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ProductPage.class)
                            )
                    }
            )
    })
    @GetMapping
    public ResponseEntity<ProductPage> getAll(
            @Parameter(description = "Page number") @RequestParam("page") Integer page
    ) {
        return ResponseEntity
                .ok(productService.getAll(page));
    }

    @Operation(summary = "Getting product by id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Product successfully received",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ProductDto.class)
                            )
                    }
            )
    })
    @GetMapping("/{product-id}")
    public ResponseEntity<ProductDto> get(
            @Parameter(description = "Product id") @PathVariable("product-id") Long id
    ) {
        return ResponseEntity
                .ok(productService.findProductDtoById(id));
    }

    @Operation(summary = "Updating existing product")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "202",
                    description = "Product successfully updated",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ProductDto.class)
                            )
                    }
            )
    })
    @PutMapping("/{product-id}")
    public ResponseEntity<ProductDto> update(
            @Parameter(description = "Product id") @PathVariable("product-id") Long id,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Updated product DTO") @RequestBody NewOrUpdatedProductDto updatedProductDto
    ) {
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(productService.updateProduct(id, updatedProductDto));
    }

    @Operation(summary = "Deleting existing product by id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "202",
                    description = "Product successfully deleted"
            )
    })
    @DeleteMapping("/{product-id}")
    public ResponseEntity<?> delete(
            @Parameter(description = "Product id") @PathVariable("product-id") Long id
    ) {
        productService.deleteProduct(id);
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .build();
    }
}

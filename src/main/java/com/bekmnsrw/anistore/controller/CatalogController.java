package com.bekmnsrw.anistore.controller;

import com.bekmnsrw.anistore.dto.ProductDto;
import com.bekmnsrw.anistore.service.CatalogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;

@Controller
@SessionAttributes("products")
@RequiredArgsConstructor
@RequestMapping("/catalog")
public class CatalogController {

    private final CatalogService catalogService;

    @GetMapping
    public String getCatalogPage(Model model) {
        List<ProductDto> products = catalogService.getAllProducts();
        model.addAttribute("products", products);
        return "catalog";
    }
}

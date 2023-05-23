package com.bekmnsrw.anistore.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/catalog")
public class CatalogController {

    @GetMapping
    public String getCatalogPage() {
        return "catalog";
    }
}

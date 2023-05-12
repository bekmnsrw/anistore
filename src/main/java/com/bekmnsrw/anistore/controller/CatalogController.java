package com.bekmnsrw.anistore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CatalogController {

    @RequestMapping("/catalog")
    public String getCatalogPage() { return "catalog"; }
}

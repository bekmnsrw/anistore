package com.bekmnsrw.anistore.controller.admin;

import com.bekmnsrw.anistore.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final ProductService productService;

    @GetMapping("/add-product")
    public String getAddProductPage() { return "admin/add_product"; }

    @GetMapping("/update-product")
    public String getUpdateProductPage(
            Model model,
            @RequestParam(value = "page", required = false) Integer page
    ) {
        model.addAttribute("products", productService.getAll(0));
        return "admin/update_product";
    }
}

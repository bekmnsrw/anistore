package com.bekmnsrw.anistore.controller;

import com.bekmnsrw.anistore.dto.ProductDto;
import com.bekmnsrw.anistore.service.CartItemService;
import com.bekmnsrw.anistore.service.CatalogService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@SessionAttributes("products")
@RequiredArgsConstructor
@RequestMapping("/catalog")
public class CatalogController {

    private final CatalogService catalogService;
    private final CartItemService cartItemService;

    private static final String ROLE_ANONYMOUS = "ROLE_ANONYMOUS";

    @GetMapping
    public ModelAndView getCatalogPage() {
        ModelAndView modelAndView = new ModelAndView();
        List<ProductDto> products = catalogService.getAllProducts();
        modelAndView.addObject("products", products);
        modelAndView.setViewName("catalog");
        return modelAndView;
    }

    @PostMapping
    public String addProductToCart(
            @RequestParam(value = "productId", required = false) Long productId,
            Model model
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String role = authentication.getAuthorities().toArray()[0].toString();

        if (role.equals(ROLE_ANONYMOUS)) {
            return "redirect:/sign-in";
        } else {
            model.addAttribute("productId", productId);
            cartItemService.addProductToCartInController(authentication.getName(), productId);
            return "catalog";
        }
    }
}

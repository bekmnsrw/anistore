package com.bekmnsrw.anistore.controller;

import com.bekmnsrw.anistore.dto.CartItemDto;
import com.bekmnsrw.anistore.service.CartItemService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;

@Controller
@RequestMapping("/cart")
@SessionAttributes("productsInCart")
@RequiredArgsConstructor
public class CartController {

    private final CartItemService cartItemService;

    @GetMapping
    public String getCartPage(
            Model model,
            HttpSession httpSession
    ) {
        List<CartItemDto> productsInCart = cartItemService.getUserCartInController(httpSession.getAttribute("email").toString());
        model.addAttribute("productsInCart", productsInCart);
        return "cart";
    }
}

package com.bekmnsrw.anistore.controller;

import com.bekmnsrw.anistore.dto.CartItemDto;
import com.bekmnsrw.anistore.service.CartItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/cart")
@SessionAttributes("productsInCart")
@RequiredArgsConstructor
public class CartController {

    private final CartItemService cartItemService;

    @GetMapping
    public ModelAndView getCartPage() {
        ModelAndView modelAndView = new ModelAndView();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        List<CartItemDto> productsInCart = cartItemService.getUserCartInController(authentication.getName());

        System.out.println(productsInCart);

        modelAndView.addObject("productsInCart", productsInCart);
        modelAndView.setViewName("cart");
        return modelAndView;
    }

    @PostMapping
    public void updateProductAmountInCart(
            @RequestParam(value = "btnPlus") String btnPlus,
            @RequestParam(value = "btnMinus") String btnMinus
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (!btnPlus.isEmpty()) {
            cartItemService
                    .increaseProductAmountInController(authentication.getName(), Long.valueOf(btnPlus));
        }

        if (!btnMinus.isEmpty()) {
            cartItemService
                    .decreaseProductAmountInController(authentication.getName(), Long.valueOf(btnMinus));
        }
    }
}

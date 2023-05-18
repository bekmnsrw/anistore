package com.bekmnsrw.anistore.controller;

import com.bekmnsrw.anistore.dto.CartItemDto;
import com.bekmnsrw.anistore.service.CartItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public String updateProductAmountInCart(
            @RequestParam(value = "btnPlus", required = false) Long btnPlus,
            @RequestParam(value = "btnMinus", required = false) Long btnMinus,
            Model model
    ) {
        model.addAttribute("btnPlus", btnPlus);
        model.addAttribute("btnMinus", btnMinus);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (btnPlus != null) {
            cartItemService.increaseProductAmountInController(authentication.getName(), btnPlus);
        }

        if (btnMinus != null) {
            cartItemService.decreaseProductAmountInController(authentication.getName(), btnMinus);
        }

        return "cart";
    }
}

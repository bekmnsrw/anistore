package com.bekmnsrw.anistore.controller;

import com.bekmnsrw.anistore.dto.form.OrderForm;
import com.bekmnsrw.anistore.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    public String getOrderPage(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Double price = orderService.getTotalOrderPrice(authentication.getName());
        OrderForm orderForm = new OrderForm();
        orderForm.setOrderPrice(price);
        model.addAttribute("totalOrderPrice", price);
        model.addAttribute("orderForm", orderForm);
        return "order";
    }

    @PostMapping
    public String makeOrder(@ModelAttribute OrderForm orderForm) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        orderService.makeOrder(orderForm, authentication.getName());
        return "redirect:/profile";
    }
}

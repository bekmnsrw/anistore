package com.bekmnsrw.anistore.controller;

import com.bekmnsrw.anistore.dto.form.OrderForm;
import com.bekmnsrw.anistore.service.OrderService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    public String getOrderPage(
            Model model,
            HttpSession httpSession
    ) {
        Double price = orderService.getTotalOrderPrice(httpSession.getAttribute("email").toString());
        OrderForm orderForm = new OrderForm();
        orderForm.setOrderPrice(price);
        model.addAttribute("totalOrderPrice", price);
        model.addAttribute("orderForm", orderForm);
        return "order";
    }

    @PostMapping
    public String makeOrder(
            @ModelAttribute OrderForm orderForm,
            HttpSession httpSession
    ) {
        orderService.makeOrder(orderForm, httpSession.getAttribute("email").toString());
        return "redirect:/profile";
    }
}

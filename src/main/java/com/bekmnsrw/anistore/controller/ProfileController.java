package com.bekmnsrw.anistore.controller;

import com.bekmnsrw.anistore.mapper.UserMapper;
import com.bekmnsrw.anistore.repository.UserRepository;
import com.bekmnsrw.anistore.service.OrderService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("userDto")
@RequiredArgsConstructor
public class ProfileController {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final OrderService orderService;

    @GetMapping("/profile")
    public String getProfilePage(
            Model model,
            HttpSession httpSession
    ) {
        model.addAttribute("orderHistory", orderService.getOrderHistory(httpSession.getAttribute("email").toString()));
        model.addAttribute("userDto", userMapper.from(userRepository.findByEmail(httpSession.getAttribute("email").toString()).get()));
        return "profile";
    }
}

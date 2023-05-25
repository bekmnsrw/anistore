package com.bekmnsrw.anistore.controller;

import com.bekmnsrw.anistore.dto.UserDto;
import com.bekmnsrw.anistore.mapper.UserMapper;
import com.bekmnsrw.anistore.repository.UserRepository;
import com.bekmnsrw.anistore.service.OrderService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    public String getProfilePage(Model model, HttpSession httpSession) {
        // TODO: replace with session attribute
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDto userDto = userMapper.from(userRepository.findByEmail(authentication.getName()).get());
        model.addAttribute("orderHistory", orderService.getOrderHistory(authentication.getName()));
        model.addAttribute("userDto", userDto);
        System.out.println(httpSession.getAttribute("email"));
        System.out.println(httpSession.getAttribute("role"));
        return "profile";
    }
}

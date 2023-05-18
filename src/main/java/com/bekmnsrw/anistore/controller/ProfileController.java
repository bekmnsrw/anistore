package com.bekmnsrw.anistore.controller;

import com.bekmnsrw.anistore.dto.UserDto;
import com.bekmnsrw.anistore.mapper.UserMapper;
import com.bekmnsrw.anistore.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@Controller
@SessionAttributes("userDto")
@RequiredArgsConstructor
public class ProfileController {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @GetMapping("/profile")
    public ModelAndView getProfilePage() {
        ModelAndView modelAndView = new ModelAndView();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDto userDto = userMapper.from(userRepository.findByEmail(authentication.getName()).get());
        modelAndView.addObject("userDto", userDto);
        modelAndView.setViewName("profile");
        return modelAndView;
    }
}

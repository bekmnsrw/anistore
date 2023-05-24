package com.bekmnsrw.anistore.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SignInController {

    @GetMapping("/sign-in")
    public String getSignInPage(
            HttpSession httpSession,
            HttpServletRequest httpServletRequest
    ) {
        Cookie[] cookies = httpServletRequest.getCookies();
        Boolean isRememberMe = false;

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("remember-me")) {
                    System.out.println(cookie.getName());
                    isRememberMe = true;
                }
            }
        }

        if (httpSession.getAttribute("email") != null || isRememberMe) {
            return "redirect:profile";
        } else {
            return "auth/sign_in";
        }
    }
}

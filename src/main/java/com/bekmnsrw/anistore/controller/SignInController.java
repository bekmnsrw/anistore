package com.bekmnsrw.anistore.controller;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SignInController {

    @GetMapping("/sign-in")
    public String getSignInPage() {
        if (isAuthenticated()) { return "redirect:profile"; }
        return "auth/sign_in";
    }

    private boolean isAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null ||
                AnonymousAuthenticationToken.class.isAssignableFrom(authentication.getClass())
        ) { return false; }
        return authentication.isAuthenticated();
    }
}

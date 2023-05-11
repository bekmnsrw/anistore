package com.bekmnsrw.anistore.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class AuthController {

    @GetMapping("/welcome")
    public String showWelcomePage() { return "welcome"; }

    @GetMapping("/about")
    public String showAboutPage() { return "about"; }

    @GetMapping("/sign-in")
    public String showSignInPage() { return "auth/sign_in"; }

    @GetMapping("/sign-up")
    public String showSignUpPage() { return "auth/sign_up"; }
}

package com.bekmnsrw.anistore.controller.auth;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SignInController {

    @GetMapping("/sign-in")
    public String getSignInPage(
            HttpSession httpSession
    ) {
        if (httpSession.getAttribute("email") != null) {
            return "redirect:profile";
        } else {
            return "auth/sign_in";
        }
    }
}

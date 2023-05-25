package com.bekmnsrw.anistore.controller.auth;

import com.bekmnsrw.anistore.dto.form.SignUpForm;
import com.bekmnsrw.anistore.service.SignUpService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class SignUpController {

    private final SignUpService signUpService;

    @GetMapping("/sign-up")
    public String getSignUpPage(
            Model model,
            HttpSession httpSession
    ) {
        if (httpSession.getAttribute("email") != null) {
            return "redirect:/profile";
        } else {
            model.addAttribute("signUpForm", new SignUpForm());
            return "auth/sign_up";
        }
    }

    @PostMapping("/sign-up")
    public String signUp(@ModelAttribute SignUpForm signUpForm) {
        try {
            signUpService.signUp(signUpForm);
        } catch (RuntimeException e) {
            return "redirect:/sign-up";
        }
        return "redirect:/sign-in";
    }
}

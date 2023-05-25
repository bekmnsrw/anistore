package com.bekmnsrw.anistore.controller.auth;

import com.bekmnsrw.anistore.dto.form.SignUpForm;
import com.bekmnsrw.anistore.service.SignUpService;
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
    public String getSignUpPage(Model model) {
        model.addAttribute("signUpForm", new SignUpForm());
        return "auth/sign_up";
    }

    @PostMapping("/sign-up")
    public String signUp(@ModelAttribute SignUpForm signUpForm) {
        return signUpService.signUp(signUpForm);
    }
}

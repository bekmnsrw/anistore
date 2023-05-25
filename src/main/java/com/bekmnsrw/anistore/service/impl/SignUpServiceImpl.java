package com.bekmnsrw.anistore.service.impl;

import com.bekmnsrw.anistore.dto.form.SignUpForm;
import com.bekmnsrw.anistore.mapper.UserMapper;
import com.bekmnsrw.anistore.model.User;
import com.bekmnsrw.anistore.repository.UserRepository;
import com.bekmnsrw.anistore.service.SignUpService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SignUpServiceImpl implements SignUpService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private static final String EMAIL_ALREADY_TAKEN = "redirect:/sign-up?error=email-already-taken";
    private static final String WEAK_PASSWORD = "redirect:/sign-up?error=weak-password";
    private static final String SUCCESS = "redirect:/sign-in";

    @Override
    public String signUp(SignUpForm signUpForm) {
        if (userRepository.findByEmail(signUpForm.getEmail()).isPresent()) {
            return EMAIL_ALREADY_TAKEN + "&firstName=" + signUpForm.getFirstName() + "&secondName=" + signUpForm.getSecondName() + "&email=" + signUpForm.getEmail();
        } else {
            if (signUpForm.getPassword().length() < 6) {
                return WEAK_PASSWORD + "&firstName=" + signUpForm.getFirstName() + "&secondName=" + signUpForm.getSecondName() + "&email=" + signUpForm.getEmail();
            } else {
                User userToSave = userMapper.from(signUpForm);
                userToSave.setPassword(passwordEncoder.encode(userToSave.getPassword()));
                userRepository.save(userToSave);
                return SUCCESS;
            }
        }
    }
}

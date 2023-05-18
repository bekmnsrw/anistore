package com.bekmnsrw.anistore.service.impl;

import com.bekmnsrw.anistore.dto.form.SignUpForm;
import com.bekmnsrw.anistore.mapper.UserMapper;
import com.bekmnsrw.anistore.model.User;
import com.bekmnsrw.anistore.repository.UserRepository;
import com.bekmnsrw.anistore.service.SignUpService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SignUpServiceImpl implements SignUpService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void signUp(SignUpForm signUpForm) {
        if (userRepository.findByEmail(signUpForm.getEmail()).isPresent()) {
            throw new RuntimeException("Email already taken");
        } else {
            User userToSave = userMapper.from(signUpForm);
            String password = userToSave.getPassword();
            userToSave.setPassword(passwordEncoder.encode(password));
            User savedUser = userRepository.save(userToSave);
            userMapper.from(savedUser);
        }
    }
}

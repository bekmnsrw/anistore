package com.bekmnsrw.anistore.mapper.impl;

import com.bekmnsrw.anistore.dto.UserDto;
import com.bekmnsrw.anistore.dto.form.SignUpForm;
import com.bekmnsrw.anistore.mapper.UserMapper;
import com.bekmnsrw.anistore.model.User;
import com.bekmnsrw.anistore.model.enums.UserRole;
import org.springframework.stereotype.Component;

@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User from(SignUpForm signUpForm) {
        return User.builder()
                .firstName(signUpForm.getFirstName())
                .secondName(signUpForm.getSecondName())
                .email(signUpForm.getEmail())
                .password(signUpForm.getPassword())
                .userRole(UserRole.ROLE_USER)
                .build();
    }

    @Override
    public UserDto from(User user) {
        return UserDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .secondName(user.getSecondName())
                .userRole(user.getUserRole())
                .build();
    }
}

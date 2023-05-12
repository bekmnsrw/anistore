package com.bekmnsrw.anistore.mapper;

import com.bekmnsrw.anistore.dto.UserDto;
import com.bekmnsrw.anistore.dto.form.SignUpForm;
import com.bekmnsrw.anistore.model.User;

public interface UserMapper {

    User from(SignUpForm signUpForm);
    UserDto from(User user);
}

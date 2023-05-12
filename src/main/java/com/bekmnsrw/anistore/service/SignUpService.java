package com.bekmnsrw.anistore.service;

import com.bekmnsrw.anistore.dto.UserDto;
import com.bekmnsrw.anistore.dto.form.SignUpForm;

public interface SignUpService {

    UserDto signUp(SignUpForm signUpForm);
}

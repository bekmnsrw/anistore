package com.bekmnsrw.anistore.service;

import com.bekmnsrw.anistore.dto.UserDto;

public interface UserService {

    UserDto findByEmail(String email);
}

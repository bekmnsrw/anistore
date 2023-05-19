package com.bekmnsrw.anistore.service.impl;

import com.bekmnsrw.anistore.dto.UserDto;
import com.bekmnsrw.anistore.mapper.UserMapper;
import com.bekmnsrw.anistore.model.User;
import com.bekmnsrw.anistore.repository.UserRepository;
import com.bekmnsrw.anistore.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserDto findByEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        return user.map(userMapper::from).orElse(null);
    }
}

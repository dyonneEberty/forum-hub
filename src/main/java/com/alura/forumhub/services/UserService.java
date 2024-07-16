package com.alura.forumhub.services;

import com.alura.forumhub.domain.user.User;
import com.alura.forumhub.domain.user.UserRegisterDTO;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    public User registerUser(UserRegisterDTO dto){
        var encoder = new BCryptPasswordEncoder();
        var encodedPassword = encoder.encode(dto.password());

        var user = new User();
        user.setLogin(dto.login());
        user.setEmail(dto.email());
        user.setPassword(encodedPassword);
        user.setActive(true);

        return user;
    }
}


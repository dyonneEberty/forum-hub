package com.alura.forumhub.controllers;

import com.alura.forumhub.domain.user.UserDTO;
import com.alura.forumhub.domain.user.UserRegisterDTO;
import com.alura.forumhub.repository.UserRepository;
import com.alura.forumhub.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<UserDTO> register(@RequestBody @Valid UserRegisterDTO userDTO){
        var user = new UserService().registerUser(userDTO);
        repository.save(user);
        return ResponseEntity.ok().body(new UserDTO(user));
    }
}

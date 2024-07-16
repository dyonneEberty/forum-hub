package com.alura.forumhub.domain.user;

public record UserRegisterDTO(
        String login,
        String email,
        String password
) {
}

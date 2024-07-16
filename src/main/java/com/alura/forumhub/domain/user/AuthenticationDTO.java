package com.alura.forumhub.domain.user;

public record AuthenticationDTO (
        String login,
        String password
) {
}

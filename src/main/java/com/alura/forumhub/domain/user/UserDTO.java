package com.alura.forumhub.domain.user;

public record UserDTO(
        Long id,
        String login,
        String email
) {
    public UserDTO(User user){
        this(user.getId(), user.getLogin(), user.getEmail());
    }
}

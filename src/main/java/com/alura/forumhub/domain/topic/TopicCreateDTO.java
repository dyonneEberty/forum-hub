package com.alura.forumhub.domain.topic;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TopicCreateDTO(
        @NotBlank
        String title,
        @NotBlank
        String message,
        @NotNull
        Long course_id
) {
}

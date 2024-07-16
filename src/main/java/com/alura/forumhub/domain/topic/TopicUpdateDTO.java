package com.alura.forumhub.domain.topic;

import jakarta.validation.constraints.NotBlank;

public record TopicUpdateDTO(
        String title,
        String message,
        Long course_id,
        TopicStatus status
) {
}

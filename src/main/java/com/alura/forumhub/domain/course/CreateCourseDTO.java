package com.alura.forumhub.domain.course;

import jakarta.validation.constraints.NotBlank;

public record CreateCourseDTO(
        @NotBlank
        String name,
        @NotBlank
        String category
) {
}

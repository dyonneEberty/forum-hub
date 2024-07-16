package com.alura.forumhub.domain.course;

public record CourseResponseDTO(
        Long id,
        String name,
        String category
) {

    public CourseResponseDTO(Course course) {
        this(course.getId(), course.getName(), course.getCategory());
    }
}

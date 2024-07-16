package com.alura.forumhub.domain.topic;

import com.alura.forumhub.domain.course.CourseResponseDTO;

import java.time.LocalDate;

public record TopicDetailDTO (
        Long id,
        String title,
        String message,
        TopicStatus status,
        CourseResponseDTO course,
        String author,
        LocalDate createdDate
) {
    public TopicDetailDTO(Topic topic) {
        this(topic.getId(), topic.getTitle(), topic.getMessage(), topic.getTopicStatus(), new CourseResponseDTO(topic.getCourse()), topic.getAuthor().getLogin(), topic.getCreatedDate());
    }
}

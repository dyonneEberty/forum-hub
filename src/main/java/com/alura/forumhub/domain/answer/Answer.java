package com.alura.forumhub.domain.answer;

import com.alura.forumhub.domain.topic.Topic;
import com.alura.forumhub.domain.user.User;

import java.time.LocalDateTime;

public class Answer {

    private Long id;
    private String message;
    private Topic topic;
    private LocalDateTime createdDate;
    private User author;
    private boolean solution;
}

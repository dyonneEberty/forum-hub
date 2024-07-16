package com.alura.forumhub.domain.topic;

import com.alura.forumhub.domain.answer.Answer;
import com.alura.forumhub.domain.course.Course;
import com.alura.forumhub.domain.user.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.sql.Update;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "Topic")
@Table(name = "topics")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String message;
    private LocalDate createdDate;

    @Enumerated(EnumType.STRING)
    private TopicStatus topicStatus;

    @Setter
    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;

    @Setter
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @Transient
    private List<Answer> answers;

    public Topic(TopicCreateDTO data, Course course, User author) {
        this.title = data.title();
        this.message = data.message();
        this.course = course;
        this.author = author;
        this.topicStatus = TopicStatus.OPEN;
        this.createdDate = LocalDate.now();
    }

    public void update(TopicUpdateDTO data, Course course) {

        if (data.title() != null) {
            this.title = data.title();
        }
        if (data.message() != null) {
            this.message = data.message();
        }
        if (data.course_id() != null) {
            this.course = course;
        }
        if (data.status() != null) {
            this.topicStatus = data.status();
        }
    }

}

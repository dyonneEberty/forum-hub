package com.alura.forumhub.controllers;

import com.alura.forumhub.domain.course.Course;
import com.alura.forumhub.domain.course.CreateCourseDTO;
import com.alura.forumhub.repository.CourseRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    CourseRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity registerCourse(@RequestBody @Valid CreateCourseDTO data){
        var course = new Course(data);
        repository.save(course);
        return ResponseEntity.ok().body("Curso criado!");
    }
}


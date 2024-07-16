package com.alura.forumhub.controllers;

import com.alura.forumhub.domain.topic.TopicCreateDTO;
import com.alura.forumhub.domain.topic.TopicDetailDTO;
import com.alura.forumhub.domain.topic.TopicUpdateDTO;
import com.alura.forumhub.repository.TopicRepository;
import com.alura.forumhub.services.TopicService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/topic")
public class TopicController {

    @Autowired
    private TopicService service;

    @Autowired
    private TopicRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity create(@RequestBody @Valid TopicCreateDTO data, Authentication authentication, UriComponentsBuilder uriBuilder) throws Exception {
        var loggedUser = authentication.getName();
        var topic = service.registerTopic(data, loggedUser);
        repository.save(topic);
        var uri = uriBuilder.path("/topic/{id}").buildAndExpand(topic.getId()).toUri();
        return ResponseEntity.created(uri).body(new TopicDetailDTO(topic));
    }

    @GetMapping
    public ResponseEntity<Page<TopicDetailDTO>> list(@PageableDefault(size = 10, sort = {"course"}) Pageable pagination){
        var page = repository.findAll(pagination).stream().map(TopicDetailDTO::new);
        return ResponseEntity.ok((Page<TopicDetailDTO>) page);
    }

    @GetMapping("/{id}")
    public ResponseEntity detail(@PathVariable Long id){
        var topic = repository.getReferenceById(id);
        return ResponseEntity.ok(new TopicDetailDTO(topic));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity update(@RequestBody TopicUpdateDTO data, Authentication authentication, @PathVariable Long id) throws Exception {
        var loggedUser = authentication.getName();
        var topic = service.updateTopic(data, loggedUser, id);
        return ResponseEntity.ok(new TopicDetailDTO(topic));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable Long id, Authentication authentication) throws Exception {
        var loggedUser = authentication.getName();
        var topic = service.deleteTopic(loggedUser, id);
        repository.delete(topic);
        return ResponseEntity.noContent().build();
    }
}

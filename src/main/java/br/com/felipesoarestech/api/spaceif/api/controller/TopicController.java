package br.com.felipesoarestech.api.spaceif.api.controller;

import br.com.felipesoarestech.api.spaceif.domain.dto.input.TopicRequest;
import br.com.felipesoarestech.api.spaceif.domain.dto.output.PageResponse;
import br.com.felipesoarestech.api.spaceif.domain.dto.output.TopicResponse;
import br.com.felipesoarestech.api.spaceif.domain.model.Topic;
import br.com.felipesoarestech.api.spaceif.domain.repository.TopicRepository;
import br.com.felipesoarestech.api.spaceif.domain.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController()
@RequestMapping("topic")
public class TopicController {

    @Autowired
    TopicRepository repository;
    @Autowired
    TopicService topicService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Topic> postTopic(@RequestParam("title") String title,
                                           @RequestParam("shortDescription") String shortDescription,
                                           @RequestParam("longDescription") String longDescription,
                                           @RequestParam(value = "image", required = false) MultipartFile image) throws IOException {
        byte [] saveImage = image != null ? image.getBytes() : null; //A imagem pode ser enviada ou não.
        return topicService.save(new TopicRequest(title,shortDescription,longDescription,saveImage));
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TopicResponse>> getAllTopics() {
        return ResponseEntity.ok(repository.findAll().stream().map(TopicResponse::new).toList());
    }

    @GetMapping(value ="/byUser",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TopicResponse>> getAllTopicsByUser(@RequestParam(value = "id", required = false) int id) {
        return ResponseEntity.ok(repository.findByUserId(id).stream().map(TopicResponse::new).toList());
    }
}


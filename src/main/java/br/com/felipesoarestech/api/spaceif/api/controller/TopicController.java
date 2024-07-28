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
    public ResponseEntity<PageResponse<TopicResponse>> getAllTopics(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<Topic> topicsPage = repository.findAll(pageable);

        List<TopicResponse> topicResponses = topicsPage.getContent().stream()
                .map(TopicResponse::new)
                .collect(Collectors.toList());

        PageResponse<TopicResponse> pageResponse = new PageResponse<>(
                topicsPage.getNumber(),
                topicsPage.getTotalPages(),
                topicResponses
        );

        return ResponseEntity.ok(pageResponse);
    }
}


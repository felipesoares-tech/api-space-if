package br.com.felipesoarestech.api.cliente.domain.service;

//import br.com.felipesoarestech.api.cliente.domain.dto.ProductResponseDTO;
import br.com.felipesoarestech.api.cliente.domain.dto.TopicRequestDTO;
import br.com.felipesoarestech.api.cliente.domain.exception.EntityNotFoundException;
import br.com.felipesoarestech.api.cliente.domain.model.Topic;
import br.com.felipesoarestech.api.cliente.domain.repository.TopicRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class TopicService {
    @Autowired
    TopicRepository topicRepository;

    @Autowired
    private ModelMapper modelMapper;

    public ResponseEntity<Topic> save(TopicRequestDTO topic){
        Topic topicSave = topicRepository.save(new Topic(topic));
        return ResponseEntity.status(HttpStatus.CREATED).body(topicSave);
    }
}

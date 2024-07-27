package br.com.felipesoarestech.api.cliente.domain.dto;

import br.com.felipesoarestech.api.cliente.domain.model.Topic;

public record ProductResponseDTO(String id, String name, Double price) {
//    public ProductResponseDTO(Topic topic){
//        this(topic.getId(), topic.getName(), topic.getPrice());
//    }
}

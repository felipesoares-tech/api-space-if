package br.com.felipesoarestech.api.spaceif.domain.dto.output;

import br.com.felipesoarestech.api.spaceif.domain.model.Topic;

public record TopicResponse(String shortDescription, String title, String author) {
        public TopicResponse(Topic topic){
        this(topic.getShortDescription(), topic.getTitle(),topic.getUser().getName());
    }
}

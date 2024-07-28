package br.com.felipesoarestech.api.spaceif.domain.dto.output;

import br.com.felipesoarestech.api.spaceif.domain.model.Topic;

public record TopicResponse(byte[] image, String shortDescription, String title) {
        public TopicResponse(Topic topic){
        this(topic.getImage(), topic.getShortDescription(), topic.getTitle());
    }
}

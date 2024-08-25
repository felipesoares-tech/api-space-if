package br.com.felipesoarestech.api.spaceif.domain.dto.output;

import br.com.felipesoarestech.api.spaceif.domain.model.Topic;

public record TopicDetailsResponse(String title, String longDescription) {
        public TopicDetailsResponse(Topic topic){
        this(topic.getTitle(),topic.getLongDescription());
    }
}

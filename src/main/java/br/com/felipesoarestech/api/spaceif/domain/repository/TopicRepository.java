package br.com.felipesoarestech.api.spaceif.domain.repository;

import br.com.felipesoarestech.api.spaceif.domain.dto.output.TopicResponse;
import br.com.felipesoarestech.api.spaceif.domain.model.Topic;
import br.com.felipesoarestech.api.spaceif.domain.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TopicRepository extends JpaRepository<Topic, String> {
    List<Topic> findByUserId(int userID);
}

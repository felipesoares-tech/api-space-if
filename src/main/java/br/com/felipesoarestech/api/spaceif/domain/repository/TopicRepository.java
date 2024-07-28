package br.com.felipesoarestech.api.spaceif.domain.repository;

import br.com.felipesoarestech.api.spaceif.domain.model.Topic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicRepository extends JpaRepository<Topic, String> { }

package br.com.felipesoarestech.api.cliente.domain.repository;

import br.com.felipesoarestech.api.cliente.domain.model.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicRepository extends JpaRepository<Topic, String> {
}

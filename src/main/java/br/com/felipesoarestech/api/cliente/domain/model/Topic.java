package br.com.felipesoarestech.api.cliente.domain.model;

import br.com.felipesoarestech.api.cliente.domain.dto.TopicRequestDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @NotNull(message = "título é obrigatório")
    private String title;
    @NotNull(message = "descrição curta é obrigatório")
    private String shortDescription;
    @NotNull(message = "descrição longa é obrigatório")
    private String longDescription;
    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte [] image;

    public Topic(TopicRequestDTO data){
        this.title = data.tittle();
        this.shortDescription = data.shortDescription();
        this.longDescription = data.longDescription();
        this.image = data.image();
    }
}
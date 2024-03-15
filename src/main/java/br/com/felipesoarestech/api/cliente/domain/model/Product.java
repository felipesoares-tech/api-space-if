package br.com.felipesoarestech.api.cliente.domain.model;

import br.com.felipesoarestech.api.cliente.domain.dto.ProductRequestDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @EqualsAndHashCode.Include
    @NotNull(message = "nome é obrigatório")
    private String name;

    private Double price;

    public Product(ProductRequestDTO data){
        this.price = data.price();
        this.name = data.name();
    }
}
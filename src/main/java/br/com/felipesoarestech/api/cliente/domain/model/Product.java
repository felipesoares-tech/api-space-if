package br.com.felipesoarestech.api.cliente.domain.model;

import br.com.felipesoarestech.api.cliente.domain.dto.ProductRequestDTO;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String name;

    private Double price;

    public Product(ProductRequestDTO data){
        this.price = data.price();
        this.name = data.name();
    }
}
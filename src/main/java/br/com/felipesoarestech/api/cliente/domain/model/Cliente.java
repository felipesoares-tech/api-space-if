package br.com.felipesoarestech.api.cliente.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull(message = "nome é obrigatório")
    private String nome;
    @Email
    @NotNull(message = "email é obrigatório")
    private String email;
    @NotNull(message = "senha é obrigatória")
    private String senha;
    LocalDateTime datLan;

    @PrePersist
    public void prePersist() {
        if (datLan == null) {
            datLan = LocalDateTime.now();
        }
    }

}

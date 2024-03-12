package br.com.felipeltda.erp.finnance.domain.dto;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ClienteDTO {
    private Integer id;
    private String nome;

    public ClienteDTO(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }
    public ClienteDTO() {}
}

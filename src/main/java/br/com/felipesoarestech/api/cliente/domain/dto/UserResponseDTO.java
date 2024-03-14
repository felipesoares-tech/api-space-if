package br.com.felipesoarestech.api.cliente.domain.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponseDTO {

    private String email;

    public UserResponseDTO(String email) {
        this.email = email;
    }
    public UserResponseDTO() {
    }
}


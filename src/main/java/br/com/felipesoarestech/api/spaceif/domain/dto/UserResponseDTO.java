package br.com.felipesoarestech.api.spaceif.domain.dto;

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


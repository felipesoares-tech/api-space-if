package br.com.felipesoarestech.api.spaceif.domain.dto.output;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponse {

    private String email;

    public UserResponse(String email) {
        this.email = email;
    }
    public UserResponse() {
    }
}


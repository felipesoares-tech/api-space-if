package br.com.felipesoarestech.api.spaceif.domain.dto.input;

import jakarta.validation.constraints.Email;

public record UserRequest(@Email String email, String name, String password, String biometricData){
}

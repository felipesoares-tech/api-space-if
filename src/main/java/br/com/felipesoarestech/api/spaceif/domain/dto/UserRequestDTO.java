package br.com.felipesoarestech.api.spaceif.domain.dto;

import jakarta.validation.constraints.Email;

public record UserRequestDTO(@Email String email,String name, String password,String biometricData){
}

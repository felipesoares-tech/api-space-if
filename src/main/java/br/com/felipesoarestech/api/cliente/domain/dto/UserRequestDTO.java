package br.com.felipesoarestech.api.cliente.domain.dto;

import jakarta.validation.constraints.Email;

public record UserRequestDTO(@Email String email, String password){
}

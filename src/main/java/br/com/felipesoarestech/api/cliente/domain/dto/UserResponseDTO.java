package br.com.felipesoarestech.api.cliente.domain.dto;

public record UserResponseDTO(String email) {
    public UserResponseDTO() {
        this(null); // Delega para o construtor canônico com um email nulo
    }
}

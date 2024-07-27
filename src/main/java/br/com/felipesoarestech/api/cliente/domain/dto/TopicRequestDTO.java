package br.com.felipesoarestech.api.cliente.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TopicRequestDTO(
        @NotBlank
        String tittle,
        @NotBlank
        String shortDescription,
        @NotBlank
        String longDescription,
        byte [] image
) {
}

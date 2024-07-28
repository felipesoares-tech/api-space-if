package br.com.felipesoarestech.api.spaceif.domain.dto;

import jakarta.validation.constraints.NotBlank;

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

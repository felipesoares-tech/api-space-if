package br.com.felipesoarestech.api.spaceif.domain.dto.input;

import jakarta.validation.constraints.NotBlank;

public record TopicRequest(
        @NotBlank
        String tittle,
        @NotBlank
        String shortDescription,
        @NotBlank
        String longDescription,
        byte [] image
) {
}

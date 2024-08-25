package br.com.felipesoarestech.api.spaceif.domain.dto.output;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record PageResponse<T>(@JsonProperty("currentPage") int currentPage, @JsonProperty("totalPages") int totalPages,@JsonProperty("content") List<T> content) { }

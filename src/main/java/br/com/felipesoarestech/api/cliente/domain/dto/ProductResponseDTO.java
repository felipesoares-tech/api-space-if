package br.com.felipesoarestech.api.cliente.domain.dto;

import br.com.felipesoarestech.api.cliente.domain.model.Product;

public record ProductResponseDTO(String id, String name, Double price) {
    public ProductResponseDTO(Product product){
        this(product.getId(), product.getName(), product.getPrice());
    }
}

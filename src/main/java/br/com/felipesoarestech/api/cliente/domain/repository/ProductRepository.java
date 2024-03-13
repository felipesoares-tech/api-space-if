package br.com.felipesoarestech.api.cliente.domain.repository;

import br.com.felipesoarestech.api.cliente.domain.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, String> {
}

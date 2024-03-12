package br.com.felipesoarestech.api.cliente.domain.repository;
import br.com.felipesoarestech.api.cliente.domain.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface ClienteRepository extends JpaRepository<Cliente, Integer > {
    UserDetails findByEmail(String email);
}

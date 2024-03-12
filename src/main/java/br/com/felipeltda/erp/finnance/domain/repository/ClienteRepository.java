package br.com.felipeltda.erp.finnance.domain.repository;
import br.com.felipeltda.erp.finnance.domain.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Integer > { }

package br.com.felipesoarestech.api.spaceif.domain.repository;
import br.com.felipesoarestech.api.spaceif.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<User, Integer > {
    UserDetails findByEmail(String email);
}

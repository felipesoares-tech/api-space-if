package br.com.felipesoarestech.api.cliente.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Collection;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Email
    @EqualsAndHashCode.Include
    @NotNull(message = "email é obrigatório")
    private String email;
    @NotNull(message = "nome é obrigatório")
    private String name;
    @NotNull(message = "senha é obrigatória")
    private String password;
    @Lob
    private byte[] biometricData;
    LocalDateTime createdAt;

    public User(String email,String name, String password, byte[] biometricData){
        this.email = email;
        this.password = password;
        this.name = name;
        this.biometricData = biometricData;
    }

    @PrePersist
    public void prePersist() {
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
    }

    public void setBiometricData(byte[] biometricData) {
        this.biometricData = biometricData;
    }

    public void setBiometricData(String biometricDataBase64) {
        this.biometricData = Base64.getDecoder().decode(biometricDataBase64);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

package br.com.felipesoarestech.api.cliente.api.controller;

import br.com.felipesoarestech.api.cliente.domain.dto.UserRequestDTO;
import br.com.felipesoarestech.api.cliente.domain.dto.LoginResponseDTO;
import br.com.felipesoarestech.api.cliente.domain.dto.UserResponseDTO;
import br.com.felipesoarestech.api.cliente.domain.exception.LinkedEntityException;
import br.com.felipesoarestech.api.cliente.domain.exception.UserPasswordNotExists;
import br.com.felipesoarestech.api.cliente.domain.model.User;
import br.com.felipesoarestech.api.cliente.domain.repository.UserRepository;
import br.com.felipesoarestech.api.cliente.domain.security.TokenService;
import br.com.felipesoarestech.api.cliente.domain.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid UserRequestDTO data){
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(),data.password());
        try{
            var auth = this.authenticationManager.authenticate(usernamePassword);

            var token = tokenService.generateToken((User) auth.getPrincipal());

            return ResponseEntity.ok(new LoginResponseDTO(token));

        }catch (AuthenticationException e){
            throw new UserPasswordNotExists("Usuário ou senha incorretos!!");
        }

    }

    @PostMapping("/login/biometric")
    public ResponseEntity<LoginResponseDTO> loginWithBiometrics(@RequestBody @Valid UserRequestDTO data) {
        User user = (User) userRepository.findByEmail(data.email());
        if (user != null && userService.verifyBiometricData(user, data.biometricData())) {
            var token = tokenService.generateToken(user);
            return ResponseEntity.ok(new LoginResponseDTO(token));
        }
        throw new UserPasswordNotExists("Autenticação biométrica falhou!");
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponseDTO register(@RequestBody @Valid UserRequestDTO data){
        //if(userRepository.findByEmail(data.email()) != null) return ResponseEntity.badRequest().build();
        String encryptPassword = new BCryptPasswordEncoder().encode(data.password());
        User user = new User(data.email(),data.name(),encryptPassword,data.biometricData());

        return userService.register(user);
    }
}

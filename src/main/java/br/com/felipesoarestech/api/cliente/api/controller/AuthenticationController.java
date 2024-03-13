package br.com.felipesoarestech.api.cliente.api.controller;

import br.com.felipesoarestech.api.cliente.domain.dto.AuthenticationDTO;
import br.com.felipesoarestech.api.cliente.domain.dto.LoginResponseDTO;
import br.com.felipesoarestech.api.cliente.domain.model.Cliente;
import br.com.felipesoarestech.api.cliente.domain.repository.ClienteRepository;
import br.com.felipesoarestech.api.cliente.domain.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private ClienteRepository clienteRepository;
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data){
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(),data.senha());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((Cliente) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));

    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid AuthenticationDTO data){
        if(clienteRepository.findByEmail(data.email()) != null) return ResponseEntity.badRequest().build();

        String encryptPassword = new BCryptPasswordEncoder().encode(data.senha());
        Cliente cliente = new Cliente(data.email(), encryptPassword);

        this.clienteRepository.save(cliente);

        return ResponseEntity.ok().build();
    }
}

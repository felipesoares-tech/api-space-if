package br.com.felipesoarestech.api.cliente.api.controller;

import br.com.felipesoarestech.api.cliente.domain.dto.ProductRequestDTO;
import br.com.felipesoarestech.api.cliente.domain.dto.ProductResponseDTO;
import br.com.felipesoarestech.api.cliente.domain.model.Product;
import br.com.felipesoarestech.api.cliente.domain.model.User;
import br.com.felipesoarestech.api.cliente.domain.repository.ProductRepository;
import br.com.felipesoarestech.api.cliente.domain.repository.UserRepository;
import br.com.felipesoarestech.api.cliente.domain.service.UserService;
import br.com.felipesoarestech.api.cliente.domain.dto.ClienteDTO;
import br.com.felipesoarestech.api.cliente.domain.exception.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController()
@RequestMapping("product")
public class ProductController {

    @Autowired
    ProductRepository repository;

    @PostMapping
    public ResponseEntity postProduct(@RequestBody @Valid ProductRequestDTO body){
        Product newProduct = new Product(body);

        this.repository.save(newProduct);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity getAllProducts(){
        List<ProductResponseDTO> productList = this.repository.findAll().stream().map(ProductResponseDTO::new).toList();

        return ResponseEntity.ok(productList);
    }
}


package br.com.felipesoarestech.api.cliente.domain.service;

import br.com.felipesoarestech.api.cliente.domain.dto.ProductResponseDTO;
import br.com.felipesoarestech.api.cliente.domain.dto.UserResponseDTO;
import br.com.felipesoarestech.api.cliente.domain.exception.DuplicateEntityException;
import br.com.felipesoarestech.api.cliente.domain.exception.EntityNotFoundException;
import br.com.felipesoarestech.api.cliente.domain.exception.LinkedEntityException;
import br.com.felipesoarestech.api.cliente.domain.model.Product;
import br.com.felipesoarestech.api.cliente.domain.model.User;
import br.com.felipesoarestech.api.cliente.domain.repository.ProductRepository;
import br.com.felipesoarestech.api.cliente.domain.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    private ModelMapper modelMapper;

    public ProductResponseDTO save(Product product){
        if(productRepository.findAll().contains(product)){
            throw new DuplicateEntityException("Produto já cadastrado. Por favor, informe outro ");
        }
        productRepository.save(product);

        return mapearParaDTO(product);
    }
    public ResponseEntity delete(String productID) {
        try {
            productRepository.deleteById(productID);
            return ResponseEntity.ok(productID);

        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException("product not found");

      }
//        catch (DataIntegrityViolationException e) {
//            throw new LinkedEntityException("product in use !!!");
//        }
    }

    public ProductResponseDTO mapearParaDTO(Product product) {
        ProductResponseDTO productResponseDTO = modelMapper.map(product, ProductResponseDTO.class);
        return productResponseDTO;
    }
}

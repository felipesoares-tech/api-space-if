package br.com.felipesoarestech.api.cliente.domain.service;

import br.com.felipesoarestech.api.cliente.domain.dto.UserResponseDTO;
import br.com.felipesoarestech.api.cliente.domain.model.User;
import br.com.felipesoarestech.api.cliente.domain.repository.UserRepository;
import br.com.felipesoarestech.api.cliente.domain.exception.DuplicateEntityException;
import br.com.felipesoarestech.api.cliente.domain.exception.EntityNotFoundException;
import br.com.felipesoarestech.api.cliente.domain.exception.LinkedEntityException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    public UserResponseDTO register(User user){
        if(userRepository.findAll().contains(user)){
            throw new DuplicateEntityException("Email já cadastrado. Por favor, faça login ou recupere sua senha");
        }
        userRepository.save(user);

        return mapearParaDTO(user);
    }
    public void delete(Integer clienteID) {
        try {
            userRepository.deleteById(clienteID);

        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException("entity not found");

        } catch (DataIntegrityViolationException e) {
            throw new LinkedEntityException("entity in use !!!");
        }
    }

    public UserResponseDTO mapearParaDTO(User user) {
        UserResponseDTO userResponseDTO = modelMapper.map(user, UserResponseDTO.class);
        return userResponseDTO;
    }

    public boolean verifyBiometricData(User user, String biometricData) {
        return user.getBiometricData().equals(biometricData);
    }
}

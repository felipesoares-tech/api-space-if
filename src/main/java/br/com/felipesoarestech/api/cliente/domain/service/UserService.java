package br.com.felipesoarestech.api.cliente.domain.service;

import br.com.felipesoarestech.api.cliente.domain.dto.ClienteDTO;
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

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    public User register(User user){
        if(userRepository.findAll().contains(user)){
            throw new DuplicateEntityException("this entity is already registered in the system !");
        }

        return userRepository.save(user);
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

    public ClienteDTO mapearParaDTO(User user) {
        ClienteDTO clienteDTO = modelMapper.map(user, ClienteDTO.class);
        return clienteDTO;
    }
}

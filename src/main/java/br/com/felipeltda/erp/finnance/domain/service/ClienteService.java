package br.com.felipeltda.erp.finnance.domain.service;

import br.com.felipeltda.erp.finnance.domain.dto.ClienteDTO;
import br.com.felipeltda.erp.finnance.domain.model.Cliente;
import br.com.felipeltda.erp.finnance.domain.repository.ClienteRepository;
import br.com.felipeltda.erp.finnance.domain.exception.DuplicateEntityException;
import br.com.felipeltda.erp.finnance.domain.exception.EntityNotFoundException;
import br.com.felipeltda.erp.finnance.domain.exception.LinkedEntityException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {
    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    private ModelMapper modelMapper;

    public Cliente cadastrar(Cliente cliente){
        if(clienteRepository.findAll().contains(cliente)){
            throw new DuplicateEntityException("this entity is already registered in the system !");
        }

        return clienteRepository.save(cliente);
    }
    public void remover(Integer clienteID) {
        try {
            clienteRepository.deleteById(clienteID);

        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException("entity not found");

        } catch (DataIntegrityViolationException e) {
            throw new LinkedEntityException("Entidade em uso !!!");
        }
    }

    public ClienteDTO mapearParaDTO(Cliente cliente) {
        ClienteDTO clienteDTO = modelMapper.map(cliente, ClienteDTO.class);
        return clienteDTO;
    }
}

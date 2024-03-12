package br.com.felipeltda.erp.finnance.api.controller;

import br.com.felipeltda.erp.finnance.domain.model.Cliente;
import br.com.felipeltda.erp.finnance.domain.repository.ClienteRepository;
import br.com.felipeltda.erp.finnance.domain.service.ClienteService;
import br.com.felipeltda.erp.finnance.domain.dto.ClienteDTO;
import br.com.felipeltda.erp.finnance.domain.exception.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cliente")
public class ClienteController {
    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/{clienteID}")
    public Cliente findById(@PathVariable Integer clienteID) {
        return clienteRepository.findById(clienteID).orElseThrow(() -> new EntityNotFoundException("CLIENTE NÃO ENCONTRADO!"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente save(@RequestBody @Valid Cliente cliente) {
        return clienteService.cadastrar(cliente);
    }

    @DeleteMapping("/{clienteID}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Integer clienteID) {
        clienteService.remover(clienteID);
    }

    @PutMapping("/{clienteID}")
    public ResponseEntity<Object> atualizarCliente(@PathVariable Integer clienteID, @RequestBody Cliente cliente) {
        Optional<Cliente> clienteAtual = clienteRepository.findById(clienteID);
        if (clienteAtual.isPresent()) {
            BeanUtils.copyProperties(cliente, clienteAtual.get(), "id");
            Cliente salvarCliente = clienteRepository.save(clienteAtual.get());
            return ResponseEntity.status(HttpStatus.OK).body(salvarCliente);
        } else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("test");
    }
    @GetMapping
    public @ResponseBody List<ClienteDTO> buscarClientes() {
        List<Cliente> listaClientes = clienteRepository.findAll();

        List<ClienteDTO> listaObjetoDTO = listaClientes.stream()
                .map(clienteService::mapearParaDTO)
                .collect(Collectors.toList());

        return listaObjetoDTO;
    }
}


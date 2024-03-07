package com.devsu.cliente.controller;

import com.devsu.cliente.dto.ClienteRequest;
import com.devsu.cliente.mapper.ClienteMapper;
import com.devsu.cliente.model.Cliente;
import com.devsu.cliente.service.ClienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService clienteService;
    private final ClienteMapper clienteMapper;

    public ClienteController(ClienteService clienteService, ClienteMapper clienteMapper) {
        this.clienteService = clienteService;
        this.clienteMapper = clienteMapper;
    }

    @GetMapping
    public List<Cliente> getAllClientes() {
        return clienteService.getAllClientes();
    }

    @GetMapping("/{clienteId}")
    public ResponseEntity<Cliente> getClienteById(@PathVariable UUID clienteId) {
        Optional<Cliente> cliente = clienteService.getClienteById(clienteId);
        return cliente.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Cliente createCliente(@RequestBody ClienteRequest clienteRequest) {
        Cliente cliente = clienteMapper.convertToCliente(clienteRequest);
        return clienteService.saveCliente(cliente);
    }

    @PutMapping("/{clienteId}")
    public ResponseEntity<Cliente> completUpdateCliente(
            @PathVariable UUID clienteId,
            @RequestBody ClienteRequest clienteRequest) {
        
        return handleUpdate(clienteId, clienteRequest);

    }

    @PatchMapping("/{clienteId}")
    public ResponseEntity<Cliente> updateCliente(
            @PathVariable UUID clienteId,
            @RequestBody  ClienteRequest clienteRequest
    ) {
        return handleUpdate(clienteId, clienteRequest);
    }

    private ResponseEntity<Cliente> handleUpdate(UUID clienteId, ClienteRequest clienteRequest) {

        Optional<Cliente> existingClienteOptional = clienteService.getClienteById(clienteId);

        if (existingClienteOptional.isPresent()) {

            Cliente existingCliente = existingClienteOptional.get();
            clienteMapper.updateClienteFromRequest(clienteRequest, existingCliente);
            Cliente updatedCliente = clienteService.saveCliente(existingCliente);

            return ResponseEntity.ok(updatedCliente);

        } else {

            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{clienteId}")
    public ResponseEntity<Void> deleteCliente(@PathVariable UUID clienteId) {
        clienteService.deleteCliente(clienteId);
        return ResponseEntity.noContent().build();
    }
}

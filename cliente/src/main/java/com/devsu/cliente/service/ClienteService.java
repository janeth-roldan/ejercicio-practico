package com.devsu.cliente.service;

import com.devsu.cliente.model.Cliente;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ClienteService {

    List<Cliente> getAllClientes();

    Optional<Cliente> getClienteById(UUID clienteId);

    Cliente saveCliente(Cliente cliente);

    void deleteCliente(UUID clienteId);
}
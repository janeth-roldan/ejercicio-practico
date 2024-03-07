package com.devsu.cliente.service;

import com.devsu.cliente.model.Cliente;
import com.devsu.cliente.repository.ClienteRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.ComponentScan;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ComponentScan(basePackages = "com.devsu.cliente")
public class ClienteServiceTest {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ClienteService clienteService;

    public ClienteServiceTest() {
        this.clienteService = new ClienteServiceImpl(clienteRepository);
    }

    @Test
    public void testGetAllClientes() {
        Cliente cliente1 = newCliente("Juan Alvarez", "Calle SN");
        Cliente cliente2 = newCliente("Carolina Almeida", "Calle SN");

        entityManager.persist(cliente1);
        entityManager.persist(cliente2);
        entityManager.flush();

        List<Cliente> clientes = clienteService.getAllClientes();
        assertThat(clientes).hasSize(2);
    }

    @Test
    public void testGetClienteById() {

        Cliente cliente = newCliente("Juan Alvarez", "Calle SN");

        entityManager.persist(cliente);
        entityManager.flush();

        Optional<Cliente> foundCliente = clienteService.getClienteById(cliente.getClienteId());

        assertThat(foundCliente).isPresent();
        assertThat(foundCliente.get().getClienteId()).isEqualTo(cliente.getClienteId());
    }

    @Test
    public void testSaveCliente() {
        Cliente cliente = newCliente("Juan Alvarez", "Calle SN");
        Cliente savedCliente = clienteService.saveCliente(cliente);
        assertThat(savedCliente.getClienteId()).isNotNull();
        assertThat(savedCliente.getNombre()).isEqualTo(cliente.getNombre());
        assertThat(savedCliente.getDireccion()).isEqualTo(cliente.getDireccion());
    }

    @Test
    public void testDeleteCliente() {
        Cliente cliente = newCliente("Juan Alvarez", "Calle SN");
        entityManager.persist(cliente);
        entityManager.flush();
        clienteService.deleteCliente(cliente.getClienteId());
        assertThat(clienteRepository.findById(cliente.getClienteId())).isEmpty();
    }

    private Cliente newCliente(String nombre, String direccion){
        Cliente cliente = new Cliente();
        cliente.setClienteId(UUID.randomUUID());
        cliente.setNombre(nombre);
        cliente.setDireccion(direccion);
        return cliente;
    }
}

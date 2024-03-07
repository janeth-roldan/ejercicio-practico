package com.devsu.cuenta.client;

import com.devsu.cuenta.dto.ClienteDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(name = "cliente-service", url = "${app.ms.cliente.url}")
public interface ClienteFeignClient {
    @GetMapping("/clientes/{clienteId}")
    ClienteDto getCliente(@PathVariable("clienteId") UUID clienteId);
}
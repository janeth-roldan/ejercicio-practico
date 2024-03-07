package com.devsu.cuenta.repository;

import com.devsu.cuenta.model.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface CuentaRepository extends JpaRepository<Cuenta, UUID> {
    List<Cuenta> findByClienteId(UUID clienteId);

}

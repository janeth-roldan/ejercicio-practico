package com.devsu.cuenta.service;

import com.devsu.cuenta.model.Cuenta;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
public interface CuentaService {
    List<Cuenta> getAllCuentas();
    Optional<Cuenta> getCuentaById(UUID cuentaId);
    Cuenta saveCuenta(Cuenta cuenta);
    void deleteCuenta(UUID cuentaId);
    List<Cuenta> getCuentasByClienteId(UUID clienteId);
}
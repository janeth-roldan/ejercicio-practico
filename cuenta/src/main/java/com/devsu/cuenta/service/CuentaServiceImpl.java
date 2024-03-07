package com.devsu.cuenta.service;

import com.devsu.cuenta.model.Cuenta;
import com.devsu.cuenta.repository.CuentaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Service
public class CuentaServiceImpl implements CuentaService {
    private final CuentaRepository cuentaRepository;
    public CuentaServiceImpl(CuentaRepository cuentaRepository) {
        this.cuentaRepository = cuentaRepository;
    }
    @Override
    public List<Cuenta> getAllCuentas() {
        return cuentaRepository.findAll();
    }
    @Override
    public Optional<Cuenta> getCuentaById(UUID cuentaId) {
        return cuentaRepository.findById(cuentaId);
    }
    @Override
    public Cuenta saveCuenta(Cuenta cuenta) {
        return cuentaRepository.save(cuenta);
    }
    @Override
    public void deleteCuenta(UUID cuentaId) {
        cuentaRepository.deleteById(cuentaId);
    }
    @Override
    public List<Cuenta> getCuentasByClienteId(UUID clienteId) {
        return cuentaRepository.findByClienteId(clienteId);
    }
}
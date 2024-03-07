package com.devsu.cuenta.service;

import com.devsu.cuenta.dto.MovimientoRequest;
import com.devsu.cuenta.model.Movimiento;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
public interface MovimientoService {
    List<Movimiento> getAllMovimientos();
    Optional<Movimiento> getMovimientoById(UUID id);
    Optional<Movimiento> createMovimiento(MovimientoRequest movimientoRequest);
    Optional<Movimiento> updateMovimiento(UUID movimientoId, MovimientoRequest movimientoRequest);
    void deleteMovimiento(UUID id);
    List<Movimiento> getMovimientosByCuentaAndDateRange(UUID cuentaId, LocalDateTime fechaInicio, LocalDateTime fechaFin);
}
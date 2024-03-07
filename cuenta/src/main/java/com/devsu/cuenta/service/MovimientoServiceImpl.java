package com.devsu.cuenta.service;

import com.devsu.cuenta.dto.MovimientoRequest;
import com.devsu.cuenta.exception.CuentaNotFoundException;
import com.devsu.cuenta.exception.SaldoNoDisponibleException;
import com.devsu.cuenta.mapper.MovimientoMapper;
import com.devsu.cuenta.model.Movimiento;
import com.devsu.cuenta.repository.CuentaRepository;
import com.devsu.cuenta.repository.MovimientoRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Service
public class MovimientoServiceImpl implements MovimientoService {
    private final MovimientoRepository movimientoRepository;
    private final MovimientoMapper movimientoMapper;
    private final CuentaRepository cuentaRepository;
    public MovimientoServiceImpl(MovimientoRepository movimientoRepository, MovimientoMapper movimientoMapper, CuentaRepository cuentaRepository) {
        this.movimientoRepository = movimientoRepository;
        this.movimientoMapper = movimientoMapper;
        this.cuentaRepository = cuentaRepository;
    }
    @Override
    public List<Movimiento> getAllMovimientos() {
        return movimientoRepository.findAll();
    }

    @Override
    public Optional<Movimiento> getMovimientoById(UUID id) {
        return movimientoRepository.findById(id);
    }

    @Override
    public Optional<Movimiento> createMovimiento(MovimientoRequest movimientoRequest) {
        UUID cuentaId = movimientoRequest.getCuentaId();

        Movimiento ultimoMovimiento = movimientoRepository.findTopByCuentaIdOrderByFechaDesc(cuentaId)
                .orElse(null);

        Movimiento nuevoMovimiento = cuentaRepository.findById(cuentaId)
                .map(cuenta -> {
                    BigDecimal nuevoSaldo = (ultimoMovimiento != null) ?
                            ultimoMovimiento.getSaldo().add(movimientoRequest.getValor()) :
                            cuenta.getSaldoInicial().add(movimientoRequest.getValor());

                    if (nuevoSaldo.compareTo(BigDecimal.ZERO) < 0) {
                        throw new SaldoNoDisponibleException("Saldo no disponible.");
                    }
                    Movimiento movimiento = movimientoMapper.convertToMovimiento(movimientoRequest);
                    movimiento.setCuenta(cuenta);
                    movimiento.setSaldo(nuevoSaldo);

                    return movimientoRepository.save(movimiento);
                })
                .orElseThrow(() -> new CuentaNotFoundException(cuentaId));

        return Optional.ofNullable(nuevoMovimiento);
    }
    @Override
    public Optional<Movimiento> updateMovimiento(UUID movimientoId, MovimientoRequest movimientoRequest) {
        Optional<Movimiento> existingMovimientoOptional = movimientoRepository.findById(movimientoId);

        return existingMovimientoOptional.map(existingMovimiento -> {
            movimientoMapper.updateMovimientoFromRequest(movimientoRequest, existingMovimiento);
            return Optional.of(movimientoRepository.save(existingMovimiento));
        }).orElse(Optional.empty());
    }

    @Override
    public void deleteMovimiento(UUID id) {
        movimientoRepository.deleteById(id);
    }

    @Override
    public List<Movimiento> getMovimientosByCuentaAndDateRange(UUID cuentaId, LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        return movimientoRepository.findByCuentaIdAndFechaBetween(cuentaId, fechaInicio, fechaFin);
    }
}

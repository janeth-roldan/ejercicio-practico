package com.devsu.cuenta.repository;

import com.devsu.cuenta.model.Movimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository
public interface MovimientoRepository extends JpaRepository<Movimiento, UUID> {
    Optional<Movimiento> findTopByCuentaIdOrderByFechaDesc(UUID cuentaId);
    List<Movimiento> findByCuentaIdAndFechaBetween(UUID cuentaId, LocalDateTime fechaInicio, LocalDateTime fechaFin);
}

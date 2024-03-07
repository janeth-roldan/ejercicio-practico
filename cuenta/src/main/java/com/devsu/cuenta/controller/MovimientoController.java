package com.devsu.cuenta.controller;

import com.devsu.cuenta.dto.MovimientoRequest;
import com.devsu.cuenta.model.Movimiento;
import com.devsu.cuenta.request.ValidationGroups;
import com.devsu.cuenta.service.MovimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/movimientos")
public class MovimientoController {

    private final MovimientoService movimientoService;

    @Autowired
    public MovimientoController(MovimientoService movimientoService) {
        this.movimientoService = movimientoService;
    }

    @GetMapping
    public List<Movimiento> getAllMovimientos() {
        return movimientoService.getAllMovimientos();
    }

    @GetMapping("/{movimientoId}")
    public ResponseEntity<Movimiento> getMovimientoById(@PathVariable UUID movimientoId) {
        Optional<Movimiento> movimiento = movimientoService.getMovimientoById(movimientoId);
        return movimiento.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Movimiento> createMovimiento(@RequestBody @Validated(ValidationGroups.OnCreate.class) MovimientoRequest movimientoRequest) {
        Optional<Movimiento> createdMovimiento = movimientoService.createMovimiento(movimientoRequest);
        return createdMovimiento.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{movimientoId}")
    public ResponseEntity<Movimiento> completeUpdateMovimiento(
            @PathVariable UUID movimientoId,
            @RequestBody @Validated(ValidationGroups.OnPutUpdate.class) MovimientoRequest movimientoRequest
    ) {
        return handleUpdate(movimientoId, movimientoRequest);
    }

    @PatchMapping("/{movimientoId}")
    public ResponseEntity<Movimiento> updateMovimiento(
            @PathVariable UUID movimientoId,
            @RequestBody MovimientoRequest movimientoRequest
    ) {
        return handleUpdate(movimientoId, movimientoRequest);
    }

    private ResponseEntity<Movimiento> handleUpdate(UUID movimientoId, MovimientoRequest movimientoRequest) {
        Optional<Movimiento> updatedMovimiento = movimientoService.updateMovimiento(movimientoId, movimientoRequest);
        return updatedMovimiento.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{movimientoId}")
    public ResponseEntity<Void> deleteMovimiento(@PathVariable UUID movimientoId) {
        movimientoService.deleteMovimiento(movimientoId);
        return ResponseEntity.noContent().build();
    }
}
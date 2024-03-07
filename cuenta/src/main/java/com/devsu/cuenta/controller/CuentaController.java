package com.devsu.cuenta.controller;

import com.devsu.cuenta.dto.CuentaRequest;
import com.devsu.cuenta.mapper.CuentaMapper;
import com.devsu.cuenta.model.Cuenta;
import com.devsu.cuenta.request.ValidationGroups;
import com.devsu.cuenta.service.CuentaService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@RestController
@RequestMapping("/cuentas")
public class CuentaController {
    private final CuentaService cuentaService;
    private final CuentaMapper cuentaMapper;
    public CuentaController(CuentaService cuentaService, CuentaMapper cuentaMapper) {
        this.cuentaService = cuentaService;
        this.cuentaMapper = cuentaMapper;
    }
    @GetMapping
    public List<Cuenta> getAllCuentas() {
        return cuentaService.getAllCuentas();
    }

    @GetMapping("/{cuentaId}")
    public ResponseEntity<Cuenta> getCuentaById(@PathVariable UUID cuentaId) {
        Optional<Cuenta> cuenta = cuentaService.getCuentaById(cuentaId);
        return cuenta.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Cuenta> createCuenta(@RequestBody @Validated(ValidationGroups.OnCreate.class) CuentaRequest cuentaRequest) {
        Cuenta cuenta = cuentaMapper.convertToCuenta(cuentaRequest);
        Cuenta savedCuenta = cuentaService.saveCuenta(cuenta);
        return ResponseEntity.ok(savedCuenta);
    }

    @PutMapping("/{cuentaId}")
    public ResponseEntity<Cuenta> completeUpdateCuenta(
            @PathVariable UUID cuentaId,
            @RequestBody @Validated(ValidationGroups.OnPutUpdate.class) CuentaRequest cuentaRequest
    ) {
        return handleUpdate(cuentaId, cuentaRequest);
    }

    @PatchMapping("/{cuentaId}")
    public ResponseEntity<Cuenta> updateCuenta(
            @PathVariable UUID cuentaId,
            @RequestBody  CuentaRequest cuentaRequest
    ) {
        return handleUpdate(cuentaId, cuentaRequest);
    }

    private ResponseEntity<Cuenta> handleUpdate(UUID cuentaId, CuentaRequest cuentaRequest) {

        Optional<Cuenta> existingCuentaOptional = cuentaService.getCuentaById(cuentaId);

        if (existingCuentaOptional.isPresent()) {

            Cuenta existingCuenta = existingCuentaOptional.get();
            cuentaMapper.updateCuentaFromRequest(cuentaRequest, existingCuenta);
            Cuenta updatedCuenta = cuentaService.saveCuenta(existingCuenta);
            return ResponseEntity.ok(updatedCuenta);

        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{cuentaId}")
    public ResponseEntity<Void> deleteCuenta(@PathVariable UUID cuentaId) {
        Optional<Cuenta> cuenta = cuentaService.getCuentaById(cuentaId);

        if (cuenta.isPresent()) {
            cuentaService.deleteCuenta(cuentaId);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
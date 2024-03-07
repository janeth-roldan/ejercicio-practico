package com.devsu.cuenta.exception;

import java.util.UUID;
public class CuentaNotFoundException extends RuntimeException {
    public CuentaNotFoundException(UUID cuentaId) {
        super("La cuenta con ID " + cuentaId + " no fue encontrada.");
    }
}

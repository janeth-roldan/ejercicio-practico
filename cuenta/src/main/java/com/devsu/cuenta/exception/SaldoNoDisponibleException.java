package com.devsu.cuenta.exception;
public class SaldoNoDisponibleException extends RuntimeException {
    public SaldoNoDisponibleException(String mensaje) {
        super(mensaje);
    }
}
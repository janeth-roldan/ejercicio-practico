package com.devsu.cuenta.service;

import com.devsu.cuenta.dto.ReporteDto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
public interface ReporteService {
    List<ReporteDto> generarReporte(LocalDateTime fechaInicio, LocalDateTime fechaFin, UUID clienteId);
}
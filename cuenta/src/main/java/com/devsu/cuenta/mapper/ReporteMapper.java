package com.devsu.cuenta.mapper;

import com.devsu.cuenta.dto.ReporteDto;
import com.devsu.cuenta.model.Cuenta;
import com.devsu.cuenta.model.Movimiento;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
@Mapper(componentModel = "spring")
public interface ReporteMapper {
    @Mapping(target = "tipo", source = "tipoCuenta")
    ReporteDto cuentaToReporteDto(Cuenta cuenta);

    @Mapping(target = "saldoDisponible", source = "saldo")
    @Mapping(target = "movimiento", source = "valor")
    ReporteDto movimientoToReporteDto(Movimiento movimiento, @MappingTarget ReporteDto reporteDto);
}

package com.devsu.cuenta.service;

import com.devsu.cuenta.client.ClienteFeignClient;
import com.devsu.cuenta.dto.ClienteDto;
import com.devsu.cuenta.dto.ReporteDto;
import com.devsu.cuenta.mapper.ReporteMapper;
import com.devsu.cuenta.model.Cuenta;
import com.devsu.cuenta.model.Movimiento;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@Service
public class ReporteServiceImpl implements ReporteService {
    private final CuentaService cuentaService;
    private final MovimientoService movimientoService;
    private final ClienteFeignClient clienteFeignClient;
    private final ReporteMapper reporteMapper;
    public ReporteServiceImpl(
            CuentaService cuentaService,
            MovimientoService movimientoService,
            ClienteFeignClient clienteFeignClient,
            ReporteMapper reporteMapper
    ) {
        this.cuentaService = cuentaService;
        this.movimientoService = movimientoService;
        this.clienteFeignClient = clienteFeignClient;
        this.reporteMapper = reporteMapper;
    }

    @Override
    public List<ReporteDto> generarReporte(LocalDateTime fechaInicio, LocalDateTime fechaFin, UUID clienteId) {


        ClienteDto clienteDto = clienteFeignClient.getCliente(clienteId);

        List<Cuenta> cuentas = cuentaService.getCuentasByClienteId(clienteId);
        List<ReporteDto> reporteDtoList = new ArrayList<>();

        for (Cuenta cuenta : cuentas) {
            List<Movimiento> movimientos = movimientoService.getMovimientosByCuentaAndDateRange(cuenta.getId(), fechaInicio, fechaFin);

            for (Movimiento movimiento : movimientos) {
                ReporteDto reporteDto = reporteMapper.cuentaToReporteDto(cuenta);
                reporteDto.setCliente(clienteDto.getNombre());
                ReporteDto movimientoDto = reporteMapper.movimientoToReporteDto(movimiento, reporteDto);
                reporteDtoList.add(movimientoDto);
            }
        }

        return reporteDtoList;
    }
}
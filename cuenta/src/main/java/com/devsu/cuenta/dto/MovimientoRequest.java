package com.devsu.cuenta.dto;

import com.devsu.cuenta.request.ValidationGroups;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;
@Getter
@Setter
public class MovimientoRequest {

    @JsonProperty("fecha")
    @NotNull( groups = {ValidationGroups.OnPutUpdate.class})
    private LocalDateTime fecha;

    @JsonProperty("tipoMovimiento")
    @NotNull( groups = {ValidationGroups.OnCreate.class, ValidationGroups.OnPutUpdate.class})
    private String tipoMovimiento;

    @JsonProperty("valor")
    @NotNull( groups = {ValidationGroups.OnCreate.class, ValidationGroups.OnPutUpdate.class})
    private BigDecimal valor;

    @JsonProperty("saldo")
    @NotNull( groups = {ValidationGroups.OnPutUpdate.class})
    private BigDecimal saldo;

    @JsonProperty("cuentaId")
    @NotNull( groups = {ValidationGroups.OnCreate.class, ValidationGroups.OnPutUpdate.class})
    private UUID cuentaId;

}
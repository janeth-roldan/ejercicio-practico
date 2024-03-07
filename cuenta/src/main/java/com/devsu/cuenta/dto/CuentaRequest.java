package com.devsu.cuenta.dto;

import com.devsu.cuenta.request.ValidationGroups;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.UUID;
@Getter
@Setter
public class CuentaRequest {

    @JsonProperty("numeroCuenta")
    @NotNull( groups = {ValidationGroups.OnCreate.class, ValidationGroups.OnPutUpdate.class})
    private String numeroCuenta;

    @NotNull( groups = {ValidationGroups.OnCreate.class, ValidationGroups.OnPutUpdate.class})
    @JsonProperty("tipoCuenta")
    private String tipoCuenta;

    @NotNull( groups = {ValidationGroups.OnCreate.class, ValidationGroups.OnPutUpdate.class})
    @JsonProperty("saldoInicial")
    private BigDecimal saldoInicial;

    @NotNull( groups = {ValidationGroups.OnCreate.class, ValidationGroups.OnPutUpdate.class})
    @JsonProperty("estado")
    private Boolean estado;

    @NotNull( groups = {ValidationGroups.OnCreate.class, ValidationGroups.OnPutUpdate.class})
    @JsonProperty("clienteId")
    private UUID clienteId;

}
package com.devsu.cliente.dto;

import com.devsu.cliente.request.ValidationGroups;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ClienteRequest {

    @JsonProperty("nombre")
    @NotNull( groups = {ValidationGroups.OnCreate.class, ValidationGroups.OnPutUpdate.class})
    private String nombre;

    @JsonProperty("genero")
    @NotNull( groups = {ValidationGroups.OnCreate.class, ValidationGroups.OnPutUpdate.class})
    private String genero;

    @JsonProperty("edad")
    @NotNull( groups = {ValidationGroups.OnCreate.class, ValidationGroups.OnPutUpdate.class})
    private int edad;

    @JsonProperty("identificacion")
    @NotNull( groups = {ValidationGroups.OnCreate.class, ValidationGroups.OnPutUpdate.class})
    private String identificacion;

    @JsonProperty("direccion")
    @NotNull( groups = {ValidationGroups.OnCreate.class, ValidationGroups.OnPutUpdate.class})
    private String direccion;

    @JsonProperty("telefono")
    @NotNull( groups = {ValidationGroups.OnCreate.class, ValidationGroups.OnPutUpdate.class})
    private String telefono;

    @JsonProperty("contrasena")
    @NotNull( groups = {ValidationGroups.OnCreate.class, ValidationGroups.OnPutUpdate.class})
    private String contrasena;

    @JsonProperty("estado")
    @NotNull( groups = {ValidationGroups.OnCreate.class, ValidationGroups.OnPutUpdate.class})
    private Boolean estado;
}

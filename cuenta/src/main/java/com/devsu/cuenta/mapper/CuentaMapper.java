package com.devsu.cuenta.mapper;

import com.devsu.cuenta.dto.CuentaRequest;
import com.devsu.cuenta.model.Cuenta;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component
public class CuentaMapper {
    private final ModelMapper modelMapper;
    @Autowired
    public CuentaMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;

        modelMapper.addMappings(new PropertyMap<CuentaRequest, Cuenta>() {
            @Override
            protected void configure() {
                skip(destination.getId());
            }
        });
    }

    public Cuenta convertToCuenta(CuentaRequest cuentaRequest) {
        return modelMapper.map(cuentaRequest, Cuenta.class);
    }

    public void updateCuentaFromRequest(CuentaRequest cuentaRequest, Cuenta existingCuenta) {
        modelMapper.map(cuentaRequest, existingCuenta);
    }
}

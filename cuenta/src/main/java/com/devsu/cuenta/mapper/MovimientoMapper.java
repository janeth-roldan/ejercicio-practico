package com.devsu.cuenta.mapper;

import com.devsu.cuenta.dto.MovimientoRequest;
import com.devsu.cuenta.model.Movimiento;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;
@Component
public class MovimientoMapper {
    private final ModelMapper modelMapper;
    public MovimientoMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        modelMapper.addMappings(new PropertyMap<MovimientoRequest, Movimiento>() {
            @Override
            protected void configure() {
                skip(destination.getId());
            }
        });
    }

    public Movimiento convertToMovimiento(MovimientoRequest movimientoRequest) {
        return modelMapper.map(movimientoRequest, Movimiento.class);
    }

    public void updateMovimientoFromRequest(MovimientoRequest movimientoRequest, Movimiento existingMovimiento) {
        modelMapper.map(movimientoRequest, existingMovimiento);
    }
}

package com.devsu.cliente.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Objects;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "cliente")
public class Cliente extends Persona {

    @Id
    @GeneratedValue(generator = "clienteId")
    @GenericGenerator(name = "clienteId", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "clienteId", updatable = false, nullable = false)
    private UUID clienteId;

    @Column(name = "contrasena")
    private String contrasena;

    @Column(name = "estado")
    private Boolean estado;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Cliente cliente = (Cliente) o;
        return Objects.equals(this.clienteId, cliente.clienteId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), clienteId);
    }
}

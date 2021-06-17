package com.nick.junitmockito.models;

import com.nick.junitmockito.models.exceptions.DineroInsuficienteException;

import java.math.BigDecimal;

public class Banco {
    private String nombre;

    public Banco(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void transferir(Cuenta origen, Cuenta destino, BigDecimal monto) throws DineroInsuficienteException {
        origen.debito(monto);
        destino.credito(monto);
    }
}

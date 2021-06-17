package com.nick.junitmockito.models;

import com.nick.junitmockito.models.exceptions.DineroInsuficienteException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Banco {
    private String nombre;
    private List<Cuenta> cuentas = new ArrayList<>();

    public Banco(){ }
    public Banco(String nombre) {
        this.nombre = nombre;
    }

    public  void addCuenta(Cuenta cuenta){
        cuentas.add(cuenta);
        cuenta.setBanco(this);
    }

    public List<Cuenta> getCuentas() {
        return cuentas;
    }

    public void setCuentas(List<Cuenta> cuentas) {
        this.cuentas = cuentas;
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

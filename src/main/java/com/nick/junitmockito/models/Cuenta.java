package com.nick.junitmockito.models;

import com.nick.junitmockito.models.exceptions.DineroInsuficienteException;

import java.math.BigDecimal;
import java.util.Objects;

public class Cuenta {
    private String persona;
    private BigDecimal saldo;

    public Cuenta(String persona, BigDecimal saldo) {
        this.persona = persona;
        this.saldo = saldo;
    }

    public String getPersona() {
        return persona;
    }

    public void setPersona(String persona) {
        this.persona = persona;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public void debito(BigDecimal monto) throws DineroInsuficienteException {
        BigDecimal saldo = this.saldo.subtract(monto);
        if (saldo.compareTo(BigDecimal.ZERO)< 0)
            throw new DineroInsuficienteException("El dinero en la cuenta es insuficiente!");
        this.saldo = saldo;
    }

    public  void credito(BigDecimal monto){
        this.saldo = this.saldo.add(monto);

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cuenta cuenta = (Cuenta) o;
        return Objects.equals(persona, cuenta.persona) && Objects.equals(saldo, cuenta.saldo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(persona, saldo);
    }
}

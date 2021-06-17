package com.nick.junitmockito.models;

import com.nick.junitmockito.models.exceptions.DineroInsuficienteException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class BancoTest {

    Banco banco;
    Cuenta cuentaOrigen;
    Cuenta cuentaDestino;

    @BeforeEach
    void beforeEach() {
        this.banco = new Banco("ICBC Bank");
        this.cuentaOrigen = new Cuenta("Juan",new BigDecimal("1000.100"));
        this.cuentaDestino = new Cuenta("Pedro",new BigDecimal("2000.200"));
    }

    @Test
    void testBanco(){
        assertEquals("ICBC Bank",banco.getNombre());
    }

    @Test
    void testCuentaTransferir() throws DineroInsuficienteException {
        banco.addCuenta(cuentaOrigen);
        banco.addCuenta(cuentaDestino);
        banco.transferir(cuentaOrigen,cuentaDestino,new BigDecimal("100.100"));

        assertAll(
                ()-> assertEquals(new BigDecimal("900.000"),cuentaOrigen.getSaldo()),
                ()-> assertEquals(new BigDecimal("2100.300"),cuentaDestino.getSaldo()),
                ()-> assertEquals(2, banco.getCuentas().size()),
                ()-> assertEquals("ICBC Bank",cuentaOrigen.getBanco().getNombre()),
                ()-> assertEquals("ICBC Bank",cuentaDestino.getBanco().getNombre()),
                ()-> assertEquals("Juan",banco.getCuentas().stream().filter(
                        cuenta -> cuenta.getPersona().equals("Juan")).findFirst().get().getPersona()),
                ()-> assertTrue(banco.getCuentas().stream().anyMatch(cuenta -> cuenta.getPersona().equals("Pedro")))
        );
    }

    @Test
    void testCuentaTransferirDineroInsuficienteException() {
        banco.addCuenta(cuentaOrigen);
        banco.addCuenta(cuentaDestino);
        assertThrows(DineroInsuficienteException.class, ()-> banco.transferir(cuentaOrigen,cuentaDestino,new BigDecimal("1000.200")));
        assertEquals(new BigDecimal("1000.100"),cuentaOrigen.getSaldo());
        assertEquals(new BigDecimal("2000.200"),cuentaDestino.getSaldo());

        assertEquals(2, banco.getCuentas().size());
    }


}
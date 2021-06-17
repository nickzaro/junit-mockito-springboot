package com.nick.junitmockito.models;

import com.nick.junitmockito.models.exceptions.DineroInsuficienteException;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class BancoTest {

    @Test
    void testBanco(){
        Banco banco = new Banco("ICBC Bank");
        assertEquals("ICBC Bank",banco.getNombre());
    }

    @Test
    void testCuentaTransferir() throws DineroInsuficienteException {
        Banco banco = new Banco("ICBC Bank");
        Cuenta cuentaOrigen = new Cuenta("Juan",new BigDecimal("1000.100"));
        Cuenta cuentaDestino = new Cuenta("Pedro",new BigDecimal("2000.200"));
        banco.transferir(cuentaOrigen,cuentaDestino,new BigDecimal("100.100"));
        assertEquals(new BigDecimal("900.000"),cuentaOrigen.getSaldo());
        assertEquals(new BigDecimal("2100.300"),cuentaDestino.getSaldo());
    }


}
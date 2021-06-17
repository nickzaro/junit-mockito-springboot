package com.nick.junitmockito.models;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class CuentaTest {

    @Test
    @DisplayName("Probando creación de una cuenta")
    void testCuenta(){
        Cuenta cuenta = new Cuenta("Andres",new BigDecimal("1000.12345"));
        assertEquals("Andres", cuenta.getPersona());
        assertEquals(1000.12345,cuenta.getSaldo().doubleValue());
        assertFalse(cuenta.getSaldo().compareTo(BigDecimal.ZERO)<0);
        assertTrue(cuenta.getSaldo().compareTo(BigDecimal.ZERO)>0);
    }

    @Test
    void testCuentaEquals(){
        Cuenta cuentaEsperada = new Cuenta("Andres",new BigDecimal("1000.12345"));
        Cuenta cuentaTest = new Cuenta("Andres",new BigDecimal("1000.12345"));
        assertEquals(cuentaEsperada,cuentaTest);
    }

}
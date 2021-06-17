package com.nick.junitmockito.models;

import com.nick.junitmockito.models.exceptions.DineroInsuficienteException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class CuentaTest {

    @Test
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

    @Test
    void testCuentaDebito() throws DineroInsuficienteException {
        Cuenta cuenta = new Cuenta("Andres",new BigDecimal("1000.12345"));
        cuenta.debito(new BigDecimal(100));
        assertNotNull(cuenta.getSaldo());
        assertEquals(new BigDecimal("900.12345"),cuenta.getSaldo());
    }

    @Test
    void testCuentaDebitoDineroInsuficienteException(){
        Cuenta cuenta = new Cuenta("Andres",new BigDecimal("1000.12345"));
        Exception exception=assertThrows(DineroInsuficienteException.class, ()-> cuenta.debito(new BigDecimal("1100")));
        assertEquals("El dinero en la cuenta es insuficiente!",exception.getMessage());
        assertEquals(new BigDecimal("1000.12345"),cuenta.getSaldo());

    }

    @Test
    void testCuentaCredito(){
        Cuenta cuenta = new Cuenta("Andres",new BigDecimal("1000.12345"));
        cuenta.credito(new BigDecimal(100));
        assertNotNull(cuenta.getSaldo());
        assertEquals(new BigDecimal("1100.12345"),cuenta.getSaldo());
    }

}
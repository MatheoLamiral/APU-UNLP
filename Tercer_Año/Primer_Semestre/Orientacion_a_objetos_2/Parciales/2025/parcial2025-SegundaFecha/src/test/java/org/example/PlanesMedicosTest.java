package org.example;

import org.example.Patrones.Afiliado;
import org.example.Patrones.Coseguro;
import org.example.Patrones.Integral;
import org.example.Patrones.Obligatorio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PlanesMedicosTest {
    private Afiliado afiliado;

    @BeforeEach
    void setUp() {
        afiliado = new Afiliado("Pedro", 2, 100000, LocalDate.of(1985, 12, 12),
                                new ArrayList(), new Obligatorio(LocalDate.of(2002,06,28), 10));
    }

    @Test
    void test(){
        this.afiliado.setPlanMedico(new Integral(LocalDate.of(2002,06,28), 10));
        assertEquals(33100, this.afiliado.calcularMonto());
        this.afiliado.setCoseguro(new Coseguro("coseguro1", 0.1, LocalDate.of(2020, 06, 28), 5000));
        assertEquals(-16900, this.afiliado.calcularMonto());
    }
}
package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class PaqueteTest {
    private Paquete paquete;
    private Decorator paqueteDecorado;

    @BeforeEach
    void setUp() {
        this.paquete = new Paquete("caja de libros", "Juan Perez", "Calle Falsa 123", 20000);
        this.paqueteDecorado = new EntregaExpressDecorator(new SeguroDecorator(this.paquete));
    }

    @Test
    void testPaqueteDescripcion() {
        assertEquals("caja de libros con seguro entrega express ", this.paqueteDecorado.getDescripcion());
    }

    @Test
    void testPaqueteCostoEnvio() {
        assertEquals(15000, this.paqueteDecorado.getCostoEnvio());
    }
}
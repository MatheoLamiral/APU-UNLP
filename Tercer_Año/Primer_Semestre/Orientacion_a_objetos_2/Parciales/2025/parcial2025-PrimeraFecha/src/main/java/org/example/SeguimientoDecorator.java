package org.example;

public class SeguimientoDecorator extends Decorator {

    public SeguimientoDecorator(InterfacePaquete paquete) {
        super(paquete);
    }

    public String getDescripcion() {
        return this.getPaquete().getDescripcion();
    }

    public double getCostoEnvio() {
        return this.getPaquete().getCostoEnvio() + 2000; // Adding a fixed cost for tracking
    }

    public double getValorDeclarado() {
        return this.getPaquete().getValorDeclarado();
    }
}

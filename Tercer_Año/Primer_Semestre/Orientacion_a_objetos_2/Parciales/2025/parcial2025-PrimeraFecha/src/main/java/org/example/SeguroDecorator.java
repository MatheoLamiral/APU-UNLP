package org.example;

public class SeguroDecorator extends Decorator {

    public SeguroDecorator(InterfacePaquete paquete) {
        super(paquete);
    }

    public String getDescripcion() {
        return this.getPaquete().getDescripcion() + " con seguro";
    }

    public double getCostoEnvio() {
        return this.getPaquete().getCostoEnvio() + this.getPaquete().getValorDeclarado() * 0.20; // Adding a fixed cost for insurance
    }

    public double getValorDeclarado() {
        return this.getPaquete().getValorDeclarado();
    }
}

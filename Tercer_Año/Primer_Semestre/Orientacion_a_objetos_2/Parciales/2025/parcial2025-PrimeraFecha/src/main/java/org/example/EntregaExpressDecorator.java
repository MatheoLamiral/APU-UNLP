package org.example;

public class EntregaExpressDecorator extends Decorator {

    public EntregaExpressDecorator(InterfacePaquete paquete) {
        super(paquete);
    }

    public String getDescripcion() {
        return this.getPaquete().getDescripcion() + " entrega express ";
    }

    public double getCostoEnvio() {
        return this.getPaquete().getCostoEnvio() + this.getPaquete().getValorDeclarado() * 0.50; // Adding a fixed cost for express delivery
    }

    public double getValorDeclarado() {
        return this.getPaquete().getValorDeclarado();
    }
}

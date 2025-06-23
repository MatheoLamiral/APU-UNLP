package org.example;

public class FragilDecorator extends Decorator {
    public FragilDecorator(InterfacePaquete paquete) {
        super(paquete);
    }

    public String getDescripcion() {
        return this.getPaquete().getDescripcion() + " frágil ";
    }

    public double getCostoEnvio() {
        return this.getPaquete().getCostoEnvio(); // Adding a fixed cost for fragile handling
    }

    public double getValorDeclarado() {
        return this.getPaquete().getValorDeclarado();
    }
}

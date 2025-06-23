package org.example;

public abstract class Decorator implements InterfacePaquete {
    private InterfacePaquete paquete;

    public Decorator(InterfacePaquete paquete) {
        this.paquete = paquete;
    }

    public String getDescripcion() {
        return this.paquete.getDescripcion() + " ";
    }

    public double getValorDeclarado() {
        return this.paquete.getValorDeclarado();
    }

    public double getCostoEnvio() {
        return this.paquete.getCostoEnvio();
    }

    protected InterfacePaquete getPaquete() {
        return this.paquete;
    }
}

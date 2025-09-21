package org.example.patrones;

public class SeguimientoDecorator extends Decorator {

    public SeguimientoDecorator(InterfacePaquete paquete) {
        super(paquete);
    }

    @Override
    public String textoAdicional() {
        return "";
    }

    @Override
    public double costoAdicional() {
        return 2000;
    }
}

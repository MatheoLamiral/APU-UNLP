package org.example.patrones;

public class SeguroDecorator extends Decorator {

    public SeguroDecorator(InterfacePaquete paquete) {
        super(paquete);
    }

    @Override
    public String textoAdicional() {
        return " con seguro";
    }

    @Override
    public double costoAdicional() {
        return this.getValorDeclarado() * 0.20;
    }
}

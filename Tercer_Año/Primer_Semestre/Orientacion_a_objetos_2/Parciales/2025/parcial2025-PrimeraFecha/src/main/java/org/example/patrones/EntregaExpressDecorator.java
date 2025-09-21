package org.example.patrones;

public class EntregaExpressDecorator extends Decorator {

    public EntregaExpressDecorator(InterfacePaquete paquete) {
        super(paquete);
    }

    @Override
    public String textoAdicional() {
        return " entrega express";
    }

    @Override
    public double costoAdicional() {
        return this.getValorDeclarado()*0.50;
    }
}

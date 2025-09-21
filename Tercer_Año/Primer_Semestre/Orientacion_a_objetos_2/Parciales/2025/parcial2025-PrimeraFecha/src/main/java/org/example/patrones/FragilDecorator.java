package org.example.patrones;

public class FragilDecorator extends Decorator {
    public FragilDecorator(InterfacePaquete paquete) {
        super(paquete);
    }

    @Override
    public String textoAdicional() {
        return " con manejo frágil";
    }

    @Override
    public double costoAdicional() {
        return 0;
    }
}

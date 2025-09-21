package org.example.Patrones;

public class CoseguroNulo implements ICoseguro {
    @Override
    public double aplicarDescuento(double valor) {
        return valor;
    }

    @Override
    public double getMontoCoberturaViajes() {
        return 0;
    }

    @Override
    public int getAntiguedad() {
        return 0;
    }
}

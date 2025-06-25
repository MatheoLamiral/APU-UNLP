package ar.edu.unlp.info.oo2.ejercicio11_Topografias;

import java.util.List;

public class Pantano implements Topografia {

    @Override
    public double calcularProporcion() {
        // Proporción de agua en una topografía de pantano
        return 0.7;
    }

    @Override
    public boolean esIgual(Topografia topografia) {
        return topografia.esIgualPantano();
    }

    @Override
    public boolean esIgualAgua() {
        return false;
    }

    @Override
    public boolean esIgualTierra() {
        return false;
    }

    @Override
    public boolean esIgualPantano() {
        return true;
    }

    @Override
    public boolean esIgualMixta(List<Topografia> topografias) {
        return false;
    }
}

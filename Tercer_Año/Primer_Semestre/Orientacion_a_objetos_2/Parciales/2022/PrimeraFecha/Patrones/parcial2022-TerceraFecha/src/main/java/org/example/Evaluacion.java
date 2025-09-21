package org.example;

public class Evaluacion extends EtapaProyecto{

    @Override
    public void aprobarEtapa(Proyecto proyecto) {
        proyecto.setState(new Confirmado());
    }

    @Override
    public void modificarMargenGanancia(Proyecto proyecto, double margen) {
        if((proyecto.precioProyecto()*0.11 <= margen) && (margen <= proyecto.precioProyecto()*0.15)){
            proyecto.setMargenGanancia(margen);
        }
    }
}

package org.example;

public class Construccion extends EtapaProyecto{

    @Override
    public void aprobarEtapa(Proyecto proyecto) {
        if ((proyecto.precioProyecto() > 0)) {
            proyecto.setState(new Confirmado());
        } else {
            throw new RuntimeException("Error en el precio del proyecto. No se puede aprobar la etapa de construcción.");
        }
        proyecto.setState(new Evaluacion());
    }

    @Override
    public void modificarMargenGanancia(Proyecto proyecto, double margen) {
        if((proyecto.precioProyecto()*0.8 <= margen) && (margen <= proyecto.precioProyecto()*0.10)){
            proyecto.setMargenGanancia(margen);
        }
    }
}

package org.example;

public abstract class EtapaProyecto {

    public abstract void aprobarEtapa(Proyecto proyecto);
    public abstract void modificarMargenGanancia(Proyecto proyecto, double margen);

    public void cancelarProyecto(Proyecto proyecto){
        proyecto.setState(new Cancelado());
    }
}

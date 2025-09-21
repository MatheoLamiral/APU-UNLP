package org.example;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Proyecto {
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private String objetivo;
    private int integrantes;
    private double montoPagoIntegrante;
    private double margenGanancia;
    private EtapaProyecto state;

    public Proyecto(LocalDate fechaInicio, LocalDate fechaFin, String objetivo, int integrantes, double montoPagoIntegrante, double margenGanancia) {
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.objetivo = objetivo;
        this.integrantes = integrantes;
        this.montoPagoIntegrante = montoPagoIntegrante;
        this.margenGanancia = margenGanancia;
        this.state = new Construccion();
    }

    public void aprobarEtapa(){
        this.state.aprobarEtapa(this);
    }

    public double costoProyecto() {
        //return this.state.costoProyecto(this);
        return (this.montoPagoIntegrante * this.integrantes) * this.diasProyecto();
    }

    private long diasProyecto() {
        return ChronoUnit.DAYS.between(this.fechaInicio, this.fechaFin);
    }

    public double precioProyecto() {
        //return this.state.precioProyecto(this);
        return this.costoProyecto() + this.margenGanancia;
    }

    public void modificarMargenGanancia() {
        this.state.modificarMargenGanancia(this);
    }

    public void cancelarProyecto() {
        this.state.cancelarProyecto(this);
    }

    public void setMargenGanancia(double margenGanancia) {
        this.margenGanancia = margenGanancia;
    }

    public void setState(EtapaProyecto newState) {
        this.state = newState;
    }
}

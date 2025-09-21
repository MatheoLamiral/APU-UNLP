package org.example.Patrones;

import java.time.LocalDate;

public abstract class PlanMedico {
    private LocalDate fechaContratacion;
    private int limitePrestaciones;

    public PlanMedico(LocalDate fechaContratacion, int limitePrestaciones) {
        this.fechaContratacion = fechaContratacion;
        this.limitePrestaciones = limitePrestaciones;
    }

    public double calcularMonto(Afiliado context, ICoseguro coseguro) {
        return this.getMontoFijo(coseguro) + this.cargoGrupoFamiliar(context, coseguro)
                                           + this.cargoCoberturaViajes(context, coseguro)
                                           + this.cargoCoberturaPorInternacion(coseguro);
    }

    public double cargoCoberturaViajes(Afiliado context, ICoseguro coseguro) {
        return context.getSalario() * 0.01 + coseguro.getMontoCoberturaViajes();
    }

    public double cargoCoberturaPorInternacion(ICoseguro coseguro) {
        return this.getMontoFijo(coseguro) * 0.05;
    }

    public abstract double cargoGrupoFamiliar(Afiliado context, ICoseguro coseguro);
    public abstract double getMontoFijo(ICoseguro coseguro);

    public LocalDate getFechaContratacion() {
        return fechaContratacion;
    }

    public int limitePrestaciones() {
        return limitePrestaciones;
    }
}
